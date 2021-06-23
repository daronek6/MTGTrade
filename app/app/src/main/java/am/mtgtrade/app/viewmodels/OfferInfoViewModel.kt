package am.mtgtrade.app.viewmodels

import am.mtgtrade.app.ui.models.Offer
import am.mtgtrade.app.util.Constants
import am.mtgtrade.app.util.TakenPhoto
import android.app.Application
import android.content.ContentValues.TAG
import android.content.Context
import android.content.pm.PackageManager
import android.net.Uri
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import java.io.File
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

@HiltViewModel
class OfferInfoViewModel @Inject constructor(@ApplicationContext private val context: Context): ViewModel() {

    val db = Firebase.firestore
    val storage = Firebase.storage
    val storageRef = storage.reference

    private val _offer = MutableLiveData<Offer>()
    private val _localImageUri = MutableLiveData<Uri>(null)

    val offer: LiveData<Offer> = _offer
    val localImageUri: LiveData<Uri> = _localImageUri

    fun onLoad(offerId: String) {
        db.collection("offers")
            .document(offerId)
            .get()
            .addOnSuccessListener {
                _offer.value = it.toObject<Offer>()
                Log.d(TAG, "Loaded offer: $offerId")

                saveImage(_offer.value!!.imgUri!!)
            }
    }

    fun saveImage(gsUrl: String) {

        val gsReference = storage.getReferenceFromUrl("${Constants.gsLocation}${gsUrl}")

        val file = File(
            File(context.applicationInfo.dataDir), gsReference.name)

        Log.d(TAG, "${gsReference.name}")
        gsReference.getFile(file).addOnSuccessListener {
            Log.d(TAG, "${file.absolutePath} ${it.bytesTransferred}")
            _localImageUri.value = Uri.fromFile(file)
        }
    }
}