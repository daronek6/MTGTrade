package am.mtgtrade.app.viewmodels

import am.mtgtrade.app.ui.models.Offer
import android.content.ContentValues.TAG
import android.net.Uri
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class OfferInfoViewModel @Inject constructor(): ViewModel() {

    val db = Firebase.firestore

    private val _offer = MutableLiveData<Offer>()

    val offer: LiveData<Offer> = _offer

    fun onLoad(offerId: String) {
        db.collection("offers")
            .document(offerId)
            .get()
            .addOnSuccessListener {
                _offer.value = it.toObject<Offer>()
                Log.d(TAG, "Loaded offer: $offerId")
            }
    }
}