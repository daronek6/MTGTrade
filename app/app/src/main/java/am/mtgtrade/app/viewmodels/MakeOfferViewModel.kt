package am.mtgtrade.app.viewmodels

import android.media.Image
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MakeOfferViewModel @Inject constructor(): ViewModel() {

    private var auth: FirebaseAuth = Firebase.auth

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

    fun onMakeOffer() {
        /*
        Dodanie nowej oferty wymiany do bazy danych
         */
    }
}