package am.mtgtrade.app

import am.mtgtrade.app.ui.Drawer
import am.mtgtrade.app.ui.DrawerScreens
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import am.mtgtrade.app.ui.theme.AppTheme
import am.mtgtrade.app.ui.views.*
import am.mtgtrade.app.ui.views.trade.CreateOfferView
import am.mtgtrade.app.ui.views.trade.FindOfferView
import am.mtgtrade.app.ui.views.trade.MyOffersView
import am.mtgtrade.app.ui.views.trade.OfferInfoView
import am.mtgtrade.app.viewmodels.LoginViewModel
import android.content.ContentValues.TAG
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.compose.material.*
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import android.os.StrictMode
import android.os.StrictMode.ThreadPolicy
import androidx.navigation.NavType
import androidx.navigation.compose.navArgument


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
//    private lateinit var auth: FirebaseAuth



    override fun onCreate(savedInstanceState: Bundle?) {
//        auth = Firebase.auth

        val policy = ThreadPolicy.Builder().permitNetwork().build()
        StrictMode.setThreadPolicy(policy)

        super.onCreate(savedInstanceState)
        setContent {
            AppTheme {
                AppMainScreen()
            }
        }

    }


    @Composable
    fun AppMainScreen() {
        val navController = rememberNavController()
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
                    startDestination = "login"
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
                            }
                        )
                    }
                    composable(DrawerScreens.Account.route) {
                        AccountView(
                            openDrawer = {
                                openDrawer()
                            }
                        )
                    }
                    composable(DrawerScreens.Trade.route) {
                        TradeView(
                            openDrawer = {
                                openDrawer()
                            }
                        )
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
                            }
                        )
                    }
                    composable("findOffer") {
                        FindOfferView(
                            openDrawer = {
                                openDrawer()
                            }
                        )
                    }
                    composable("findOffer/{id}", arguments = listOf(navArgument("id") { type = NavType.LongType })) {
                        OfferInfoView(
                            openDrawer = {
                                openDrawer()
                            }
                        )
                    }
                }
            }
        }
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