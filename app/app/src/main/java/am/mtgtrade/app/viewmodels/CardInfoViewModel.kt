package am.mtgtrade.app.viewmodels

import am.mtgtrade.app.ui.models.CardInfo
import am.mtgtrade.app.util.TypeConverter
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.magicthegathering.kotlinsdk.api.MtgCardApiClient
import io.magicthegathering.kotlinsdk.model.card.MtgCard
import kotlinx.coroutines.launch
import retrofit2.Response
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class CardInfoViewModel @Inject constructor(): ViewModel() {

    private val _search = MutableLiveData<String>("")
    private val _cardInfo = MutableLiveData<CardInfo>()
    private val _name = MutableLiveData<String>("")
    private val _rarity = MutableLiveData<String>("")
    private val _setName = MutableLiveData<String>("")

    private val _uri = MutableLiveData<String>("")

    val uri: LiveData<String> = _uri
    val search: LiveData<String> = _search
    val cardInfo: LiveData<CardInfo> = _cardInfo
    val name: LiveData<String> = _name
    val rarity: LiveData<String> = _rarity
    val setName: LiveData<String> = _setName

    fun onSearchUpdate(searchUpdate: String) {
        _search.value = searchUpdate
    }

    fun updateResult() {
        _name.value = _cardInfo.value!!.name
        _rarity.value = _cardInfo.value!!.rarity
        _setName.value = _cardInfo.value!!.setName
    }

    fun updatePhotoUri(newUri: String) {
        _uri.value = newUri
    }

    fun onSearch() {
        viewModelScope.run {

            val searchResult: Response<List<MtgCard>> = MtgCardApiClient.getCardsByPartialName(_search.value!!, 1, 1)
            if(searchResult.isSuccessful) {
                try {
                    _cardInfo.value = TypeConverter.MtgCardToCardInfo(searchResult.body()!!.first())
                    updateResult()
                } catch (e:Exception) {
                    _name.value = "Nie znaleziono"
                    _rarity.value = ""
                    _setName.value = ""
                }
            }
        }
    }




}