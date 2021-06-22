package am.mtgtrade.app.viewmodels

import am.mtgtrade.app.MainActivity
import am.mtgtrade.app.util.Resource
import android.content.ContentValues.TAG
import android.os.Build
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import dagger.Component
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ActivityContext
import java.util.concurrent.Executor
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor() : ViewModel() {

    private var auth: FirebaseAuth = Firebase.auth

    private val _email = MutableLiveData("")
    private val _password = MutableLiveData("")
    private val _result = MutableLiveData<Resource<String>>()

    val email: LiveData<String> = _email
    val password: LiveData<String> = _password
    val result: LiveData<Resource<String>> = _result

    fun onEmailUpdate(emailUpdate: String) {
        _email.value = emailUpdate
    }

    fun onPasswordUpdate(passwordUpdate: String) {
        _password.value = passwordUpdate
    }

    fun onLogin() {
        if (_email.value.isNullOrBlank() or _password.value.isNullOrBlank()) {
            _result.value = Resource.Error("No values")
        } else {
            firebaseLogin()
        }
    }

    private fun firebaseLogin() {
        auth.signInWithEmailAndPassword(_email.value!!, _password.value!!)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    _result.value = Resource.Success("Login successful!")
                    Log.d(TAG, "signInWithEmail:success")
                } else {
                    _result.value = Resource.Error(task.exception.toString())
                    Log.w(TAG, "signInWithEmail:failure", task.exception)
                }
            }
    }
}