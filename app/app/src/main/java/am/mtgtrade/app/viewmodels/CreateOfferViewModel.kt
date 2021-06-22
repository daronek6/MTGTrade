package am.mtgtrade.app.viewmodels

import am.mtgtrade.app.ui.models.Offer
import android.content.ContentValues.TAG
import android.media.Image
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.lifecycle.HiltViewModel
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class CreateOfferViewModel @Inject constructor(): ViewModel() {

    private var auth: FirebaseAuth = Firebase.auth
    val db = Firebase.firestore

    private val _name = MutableLiveData<String>()
    private val _img = MutableLiveData<Image>() //Raczej inny typ ale to się zobaczy

    val name: LiveData<String> = _name
    val img: LiveData<Image> = _img

    fun onNameUpdate(nameUpdate: String) {
        _name.value = nameUpdate
    }

    fun onImgUpdate(imgUpdate: Image) {
        _img.value = imgUpdate
    }

    fun onCreateOffer() {
        /*
        Dodać img do clouda i zapamiętać ścieżke
         */

        val uri = "/"

        var userName = auth.currentUser!!.displayName
        var email = auth.currentUser!!.email
        var phone = auth.currentUser!!.phoneNumber
        var id = "0"
        if (userName == null) userName = " "
        if (email == null) email = " "
        if (phone == null) phone = " "

        val date = LocalDate.now().toString()

        val newOffer = Offer(
            id = id,
            userName = userName,
            userEmail = email,
            userPhone = phone,
            cardName = _name.value!!,
            date = date,
            imgUri = uri
            )

        db.collection("offers")
            .add(newOffer)
            .addOnSuccessListener {
                Log.d(TAG, "Dodano oferte!")
                id = it.id

                val newOffer2 = Offer(
                    id = id,
                    userName = userName,
                    userEmail = email,
                    userPhone = phone,
                    cardName = _name.value!!,
                    date = date,
                    imgUri = uri
                )

                db.collection("offers")
                    .document(id)
                    .set(newOffer2)
                    .addOnSuccessListener {
                        Log.d(TAG, "Zaktualizowano id: $id")
                    }

            }
            .addOnFailureListener {
                Log.d(TAG, "$it")
            }

    }

}