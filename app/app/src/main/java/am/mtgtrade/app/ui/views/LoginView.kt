package am.mtgtrade.app.ui.views

import am.mtgtrade.app.ui.DrawerScreens
import am.mtgtrade.app.ui.theme.AppTheme
import am.mtgtrade.app.util.Resource
import am.mtgtrade.app.viewmodels.LoginViewModel
import android.content.res.Configuration
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser


@Composable
fun LoginView(
    viewModel: LoginViewModel = hiltViewModel(),
    navController: NavController
) {
//    val user: FirebaseUser? = FirebaseAuth.getInstance().currentUser
//    user.let {
//        navController.navigate(DrawerScreens.CardInfo.route) {
//            popUpTo("login") { inclusive = true }
//        }
//    }

    val email: String by viewModel.email.observeAsState("")
    val password: String by viewModel.password.observeAsState("")
    val result by viewModel.result.observeAsState(Resource.Loading())

    when(result) {
        is Resource.Success<String> -> {
            navController.navigate(DrawerScreens.CardInfo.route) {
                popUpTo("login") { inclusive = true }
            }
        }
        is Resource.Error<String> -> {
            Toast.makeText(LocalContext.current, "Wrong email or password", Toast.LENGTH_SHORT).show()
        }
        else -> {

        }
    }

    Surface(
        color = MaterialTheme.colors.background,
        modifier = Modifier
            .fillMaxSize(),
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
        ) {

            LoginHeader()

            EmailInput(email, viewModel::onEmailUpdate)

            Spacer(Modifier.height(16.dp))

            PasswordInput(password, viewModel::onPasswordUpdate)

            Spacer(Modifier.height(16.dp))

            LoginButton(viewModel::onLogin)

            Spacer(Modifier.height(16.dp))

            RegisterButton(navController)
        }
    }
}

@Composable
private fun RegisterButton(navController: NavController) {
    Button(
        colors = ButtonDefaults.buttonColors(
            backgroundColor = MaterialTheme.colors.primary
        ),
        shape = MaterialTheme.shapes.medium,
        onClick = {
            navController.navigate("register")
        },
        modifier = Modifier
            .fillMaxWidth()
            .height(48.dp)
            .padding(horizontal = 16.dp)
    ) {
        Text(text = "Register")
    }
}

@Composable
private fun LoginButton(onClick: () -> Unit) {
    Button(
        colors = ButtonDefaults.buttonColors(
            backgroundColor = MaterialTheme.colors.secondary
        ),
        shape = MaterialTheme.shapes.medium,
        onClick = {
            onClick()
        },
        modifier = Modifier
            .fillMaxWidth()
            .height(48.dp)
            .padding(horizontal = 16.dp)
    ) {
        Text(text = "Log in")
    }
}


@Composable
private fun PasswordInput(value: String, onTextChange: (String) -> Unit) {
    OutlinedTextField(
        value = value,
        onValueChange = { onTextChange(it) },
        label = {
            Text(text = "Password")
        },
        singleLine = true,
        keyboardOptions = KeyboardOptions.Default.copy(
            keyboardType = KeyboardType.Password
        ),
        visualTransformation = PasswordVisualTransformation(),
        modifier = Modifier
            .fillMaxWidth()
    )
}

@Composable
private fun EmailInput(value: String, onTextChange: (String) -> Unit) {
    OutlinedTextField(
        value = value,
        onValueChange = { onTextChange(it) },
        label = {
            Text(text = "Email address")
        },
        singleLine = true,
        keyboardOptions = KeyboardOptions.Default.copy(
            keyboardType = KeyboardType.Email
        ),
        modifier = Modifier
            .fillMaxWidth()
    )
}

@Composable
private fun LoginHeader() {
    Text(
        text = "Log in with email",
        style = MaterialTheme.typography.h4,
        modifier = Modifier
            .paddingFromBaseline(
                top = 184.dp,
                bottom = 16.dp,
            )
    )
}

@Preview(
    name = "Night Mode",
    uiMode = Configuration.UI_MODE_NIGHT_YES,
)
@Preview(
    name = "Day Mode",
    uiMode = Configuration.UI_MODE_NIGHT_NO,
)
@Composable
private fun LoginScreenPreview() {
    AppTheme {
        LoginView(navController = rememberNavController())
    }
}