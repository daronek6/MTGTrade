package am.mtgtrade.app.viewmodels

import am.mtgtrade.app.util.Resource
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AccountViewModel @Inject constructor(): ViewModel() {

    private var auth: FirebaseAuth = Firebase.auth

    private val _name = MutableLiveData<String>()
    private val _email = MutableLiveData<String>()
    private val _phone = MutableLiveData<String>()
    private val _result = MutableLiveData<Resource<String>>()

    init {
        onLoad()
    }

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

    fun onLogOut() {
       auth.signOut()
    }

}