package am.mtgtrade.app.viewmodels

import am.mtgtrade.app.util.TakenPhoto
import am.mtgtrade.app.ui.models.Offer
import android.content.ContentValues.TAG
import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class CreateOfferViewModel @Inject constructor(@ApplicationContext private val context: Context): ViewModel() {

    private var auth: FirebaseAuth = Firebase.auth
    val db = Firebase.firestore
    val storage = Firebase.storage
    val storageRef = storage.reference

    private val _name = MutableLiveData<String>()
    private val _uploading = MutableLiveData<Boolean>(false)

    val name: LiveData<String> = _name
    val uploading: LiveData<Boolean> = _uploading

    fun onNameUpdate(nameUpdate: String) {
        _name.value = nameUpdate
    }

    fun onCreateOffer(): String {
        var message = "Dodano oferte!"

        var uri = "/"

        if(_name.value.isNullOrBlank()) return "Wpisz nazwę karty!"

        if(TakenPhoto.uri != null) {
            uri = TakenPhoto.uri!!.path!!
            Log.d(TAG, "onCreateOffer() URI: $uri")

            val photoRef = storageRef.child(uri)
            val uploadTask = photoRef.putFile(TakenPhoto.uri!!)
                .addOnCompleteListener { task->
                    if(task.isSuccessful) {

                        photoRef.downloadUrl
                            .addOnSuccessListener {
                                val downloadUri = it.lastPathSegment

                                Log.d(TAG, "Download URI: ${downloadUri}")
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
                                    imgUri = downloadUri
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
                                            imgUri = downloadUri
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
                    else {
                        Log.d(TAG, "Upload error!")
                        message = "Błąd podczas wysyłania!"
                    }
                }

        }
        else {
            Log.d(TAG, "Add photo!")
            message = "Dodaj zdjęcie!"
        }

        return message
    }

    fun readTextFromImage() {
        am.mtgtrade.app.util.TextRecognition.readTextFromImage(context, _name)
    }

}