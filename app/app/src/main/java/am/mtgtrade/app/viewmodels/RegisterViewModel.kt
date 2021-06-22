package am.mtgtrade.app.viewmodels

import am.mtgtrade.app.util.Resource
import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.auth.ktx.userProfileChangeRequest
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(): ViewModel() {

    private var auth: FirebaseAuth = Firebase.auth

    private val _name = MutableLiveData<String>()
    private val _email = MutableLiveData<String>()
    private val _password = MutableLiveData<String>()
    private val _result = MutableLiveData<Resource<String>>()

    val name: LiveData<String> = _name
    val email: LiveData<String> = _email
    val password: LiveData<String> = _password
    val result: LiveData<Resource<String>> = _result

    fun onNameUpdate(nameUpdate: String) {
        _name.value = nameUpdate
    }

    fun onEmailUpdate(emailUpdate: String) {
        _email.value = emailUpdate
    }

    fun onPasswordUpdate(passwordUpdate: String) {
        _password.value = passwordUpdate
    }

    fun onRegister() {
        if (name.value.isNullOrBlank() or email.value.isNullOrBlank() or password.value.isNullOrBlank()) {
            _result.value = Resource.Error("Blank")
        } else {
            firebaseRegister()
        }
    }

    private fun firebaseRegister() {
        auth.createUserWithEmailAndPassword(_email.value!!, _password.value!!)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information

                    Log.d(TAG, "createUserWithEmail:success")

                    val profileUpdates = userProfileChangeRequest {
                        displayName = _name.value

                    }

                    auth.currentUser!!.updateProfile(profileUpdates).
                    addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            Log.d(TAG, "User profile updated.")
                        }
                    }

                    _result.value = Resource.Success("User created!")

                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(TAG, "createUserWithEmail:failure", task.exception)

                    _result.value = Resource.Error(task.exception.toString())
                }
            }
    }
}