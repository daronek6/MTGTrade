package am.mtgtrade.app.viewmodels

import am.mtgtrade.app.ui.models.Offer
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SearchOffersViewModel @Inject constructor(): ViewModel() {

    private val _search = MutableLiveData<String>()
    private val _offers = MutableLiveData<List<Offer>>()

    val search: LiveData<String> = _search
    val offers: LiveData<List<Offer>> = _offers

    fun onSearch() {
        /*
        Pobranie z bazy ofert dla kart o nazwie z _search
         */
    }

}