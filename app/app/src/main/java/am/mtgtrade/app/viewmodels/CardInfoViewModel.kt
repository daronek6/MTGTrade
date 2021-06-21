package am.mtgtrade.app.viewmodels

import am.mtgtrade.app.ui.models.CardInfo
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CardInfoViewModel @Inject constructor(): ViewModel() {

    private val _search = MutableLiveData<String>()
    private val _cardInfo = MutableLiveData<CardInfo>()
    private val _name = MutableLiveData<String>()
    private val _price = MutableLiveData<Double>()

    val search: LiveData<String> = _search
    val cardInfo: LiveData<CardInfo> = _cardInfo
    val name: LiveData<String> = _name
    val price: LiveData<Double> = _price

    fun onSearchUpdate(searchUpdate: String) {
        _search.value = searchUpdate
    }

    fun onSearch() {
        /*
         Pobranie informacji o karcie https://github.com/MagicTheGathering/mtg-sdk-kotlin
         */
    }




}