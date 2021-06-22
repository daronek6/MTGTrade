package am.mtgtrade.app.viewmodels

import am.mtgtrade.app.ui.models.Offer
import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObjects
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FindOfferViewModel @Inject constructor(): ViewModel() {

    private var auth: FirebaseAuth = Firebase.auth
    val db = Firebase.firestore

    private val _search = MutableLiveData<String>()
    private val _offers = MutableLiveData<List<Offer>>()

    val search: LiveData<String> = _search
    val offers: LiveData<List<Offer>> = _offers

    fun onSearchUpdate(searchUpdate: String) {
        _search.value = searchUpdate
    }

    fun onSearch() {
        db.collection("offers")
            .whereEqualTo("cardName", _search.value)
            .get().addOnSuccessListener { results ->
                _offers.value = results.toObjects<Offer>()
                Log.d(TAG, "Found ${_offers.value}")
            }
    }

    fun isOfferFromCurrentUser(offer: Offer): Boolean {
        return (auth.currentUser!!.email == offer.userEmail)
    }

}