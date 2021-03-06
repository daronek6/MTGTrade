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
class MyOffersViewModel @Inject constructor(): ViewModel() {

    private var auth: FirebaseAuth = Firebase.auth
    val db = Firebase.firestore

    init {
        onLoad()
    }

    private val _myOffers = MutableLiveData<List<Offer>>()

    val myOffers: LiveData<List<Offer>> = _myOffers

    fun onLoad() {
        db.collection("offers")
            .whereEqualTo("userEmail", auth.currentUser!!.email)
            .get().addOnSuccessListener { results ->
                _myOffers.value = results.toObjects<Offer>()
            }
    }

    fun removeOffer(offerId: String) {

        db.collection("offers")
            .document(offerId)
            .delete()
            .addOnSuccessListener {
                Log.d(TAG, "Removed offer!")
                onLoad()
            }
    }
}