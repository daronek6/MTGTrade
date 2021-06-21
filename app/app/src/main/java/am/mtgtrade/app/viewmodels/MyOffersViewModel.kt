package am.mtgtrade.app.viewmodels

import am.mtgtrade.app.ui.models.Offer
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MyOffersViewModel @Inject constructor(): ViewModel() {

    private var auth: FirebaseAuth = Firebase.auth

    private val _myOffers = MutableLiveData<List<Offer>>()

    val myOffers: LiveData<List<Offer>> = _myOffers

    fun onLoad() {
        /*
        Załadować wszystkie oferty wystawione przez aktualnie zalogowanego użytkownika
         */
    }

    fun removeOffer(offerId: Int) {
        /*
        Usunięcie z bazy oferty
         */
    }
}