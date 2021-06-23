package am.mtgtrade.app

import am.mtgtrade.app.ui.Drawer
import am.mtgtrade.app.ui.DrawerScreens
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import am.mtgtrade.app.ui.theme.AppTheme
import am.mtgtrade.app.ui.views.*
import am.mtgtrade.app.ui.views.camera.SimpleCameraPreview
import am.mtgtrade.app.ui.views.trade.*
import android.Manifest
import android.content.pm.PackageManager
import am.mtgtrade.app.ui.views.trade.*
import androidx.compose.material.*
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import android.os.StrictMode
import android.os.StrictMode.ThreadPolicy
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.navigation.NavType
import androidx.navigation.compose.navArgument
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import java.io.File


@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
//        auth = Firebase.auth

        val policy = ThreadPolicy.Builder().permitNetwork().build()
        StrictMode.setThreadPolicy(policy)

        super.onCreate(savedInstanceState)
        if (allPermissionsGranted()) {
            setViewContent()
        } else {
            ActivityCompat.requestPermissions(
                this, REQUIRED_PERMISSIONS, REQUEST_CODE_PERMISSIONS
            )
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int, permissions: Array<String>, grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == REQUEST_CODE_PERMISSIONS) {
            if (allPermissionsGranted()) {
                setViewContent()
            } else {
                Toast.makeText(
                    this,
                    getString(R.string.permission_message),
                    Toast.LENGTH_SHORT
                ).show()
                finish()
            }
        }
    }

    private fun setViewContent() {
        setContent {
            AppTheme {
                AppMainScreen()
            }
        }
    }

    private fun allPermissionsGranted() = REQUIRED_PERMISSIONS.all {
        ContextCompat.checkSelfPermission(
            baseContext, it
        ) == PackageManager.PERMISSION_GRANTED
    }

    @Composable
    fun AppMainScreen() {
        val navController = rememberNavController()

        val user: FirebaseUser? = FirebaseAuth.getInstance().currentUser
        var startDest = "login"
        if (user != null) {
            startDest = DrawerScreens.CardInfo.route
        }

        Surface(color = MaterialTheme.colors.background) {
            val drawerState = rememberDrawerState(DrawerValue.Closed)
            val scope = rememberCoroutineScope()
            val openDrawer = {
                scope.launch {
                    drawerState.open()
                }
            }
            ModalDrawer(
                drawerState = drawerState,
                gesturesEnabled = drawerState.isOpen,
                drawerContent = {
                    Drawer(
                        onDestinationClicked = { route ->
                            scope.launch {
                                drawerState.close()
                            }
                            navController.navigate(route) {
                                popUpTo("cardInfo")
                                launchSingleTop = true
                            }
                        }
                    )
                }
            ) {
                NavHost(
                    navController = navController,
                    startDestination = startDest
                ) {
                    composable("login") {
                        LoginView(
                            navController = navController
                        )
                    }
                    composable("register") {
                        RegisterView(
                            navController = navController
                        )
                    }
                    composable(DrawerScreens.CardInfo.route) {
                        CardInfoView(
                            openDrawer = {
                                openDrawer()
                            },
                            navController = navController
                        )
                    }
                    composable(DrawerScreens.Account.route) {
                        AccountView(
                            openDrawer = { openDrawer() },
                            navController = navController
                        )
                    }
                    composable(DrawerScreens.Trade.route) {
                        TradeView(
                            openDrawer = { openDrawer() },
                            navController = navController )
                    }
                    composable("myOffers") {
                        MyOffersView(
                            openDrawer = {
                                openDrawer()
                            }
                        )
                    }
                    composable("createOffer") {
                        CreateOfferView(
                            openDrawer = {
                                openDrawer()
                            },
                            navController = navController
                        )
                    }
                    composable("findOffer") {
                        FindOfferView(
                            openDrawer = { openDrawer() },
                            navController = navController
                        )
                    }
                    composable("findOffer/{id}", arguments = listOf(navArgument("id") { type = NavType.StringType })) {
                        OfferInfoView(
                            openDrawer = { openDrawer() },
                            it.arguments!!.getString("id")
                        )
                    }
                    composable("camera") {
                        SimpleCameraPreview()
                    }
                }
            }
        }
    }

    companion object {
        private const val REQUEST_CODE_PERMISSIONS = 10
        private val REQUIRED_PERMISSIONS = arrayOf(Manifest.permission.CAMERA)
        private const val IMMERSIVE_FLAG_TIMEOUT = 500L
    }


//    public override fun onStart() {
//        super.onStart()
//        // Check if user is signed in (non-null) and update UI accordingly.
//        val currentUser = auth.currentUser
//        if(currentUser != null){
//            reload();
//        }
//    }
//
//    private fun reload() {
//        Log.i("MainActivity", "reloaded")
//    }
//
//    private fun createAccount(email: String, password: String) {
//        // [START create_user_with_email]
//        auth.createUserWithEmailAndPassword(email, password)
//            .addOnCompleteListener(this) { task ->
//                if (task.isSuccessful) {
//                    // Sign in success, update UI with the signed-in user's information
//
//                    Log.d(TAG, "createUserWithEmail:success")
//                    val user = auth.currentUser
//                    sendEmailVerification()
//                    updateUI(user)
//                } else {
//                    // If sign in fails, display a message to the user.
//                    Log.w(TAG, "createUserWithEmail:failure", task.exception)
//                    Toast.makeText(baseContext, "Authentication failed.",
//                        Toast.LENGTH_SHORT).show()
//                    updateUI(null)
//                }
//            }
//        // [END create_user_with_email]
//    }
//
//    private fun signIn(email: String, password: String) {
//        // [START sign_in_with_email]
//        auth.signInWithEmailAndPassword(email, password)
//            .addOnCompleteListener(this) { task ->
//                if (task.isSuccessful) {
//                    // Sign in success, update UI with the signed-in user's information
//                    Log.d(TAG, "signInWithEmail:success")
//                    val user = auth.currentUser
//                    updateUI(user)
//                } else {
//                    // If sign in fails, display a message to the user.
//                    Log.w(TAG, "signInWithEmail:failure", task.exception)
//                    Toast.makeText(baseContext, "Authentication failed.",
//                        Toast.LENGTH_SHORT).show()
//                    updateUI(null)
//                }
//            }
//        // [END sign_in_with_email]
//    }
//
//    private fun sendEmailVerification() {
//        // [START send_email_verification]
//        val user = auth.currentUser!!
//        user.sendEmailVerification()
//            .addOnCompleteListener(this) { task ->
//                // Email Verification sent
//            }
//        // [END send_email_verification]
//    }
//
//    private fun updateUI(user: FirebaseUser?) {
//
//    }
//
//}

}