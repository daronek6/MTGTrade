package am.mtgtrade.app.viewmodels

import am.mtgtrade.app.ui.models.Offer
import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class OfferDetailsViewModel @Inject constructor(): ViewModel() {

    private val _offer = MutableLiveData<Offer>()
    private val _userName = MutableLiveData<String>()
    private val _cardName = MutableLiveData<String>()
    private val _email = MutableLiveData<String>()
    private val _phone = MutableLiveData<String>()
    private val _date = MutableLiveData<String>()
    private val _imgUri = MutableLiveData<Uri>()

    val offer: LiveData<Offer> = _offer
    val userName: LiveData<String> = _userName
    val cardName: LiveData<String> = _cardName
    val email: LiveData<String> = _email
    val phone: LiveData<String> = _phone
    val date: LiveData<String> = _date
    val imgUri: LiveData<Uri> = _imgUri

    fun onLoad(offerId: Int) {
        /*
        Załadwać dane do zmiennych z oferty z bazy danych o id offerId
         */
    }
}