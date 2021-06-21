package am.mtgtrade.app.viewmodels

import am.mtgtrade.app.util.Resource
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class ProfileViewModel {

    private var auth: FirebaseAuth = Firebase.auth

    private val _name = MutableLiveData<String>()
    private val _email = MutableLiveData<String>()
    private val _phone = MutableLiveData<String>()
    private val _result = MutableLiveData<Resource<String>>()

    val name: LiveData<String> = _name
    val email: LiveData<String> = _email
    val phone: LiveData<String> = _phone
    val result: LiveData<Resource<String>> = _result

    fun onLoad() {
        _name.value = auth.currentUser!!.displayName
        _email.value = auth.currentUser!!.email
        _phone.value = auth.currentUser!!.phoneNumber
    }

//    fun onPhoneUpdate(phoneUpdated: String) {
//        _phone.value = phoneUpdated
//    }

    fun onProfileUpdate() {
        /*
        Wygląda na to, że nie wiele informacji da się zmienić
         */
    }

}