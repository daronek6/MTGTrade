package am.mtgtrade.app.ui.views

import am.mtgtrade.app.ui.DrawerScreens
import am.mtgtrade.app.ui.theme.AppTheme
import am.mtgtrade.app.util.Resource
import am.mtgtrade.app.viewmodels.LoginViewModel
import am.mtgtrade.app.viewmodels.RegisterViewModel
import android.content.res.Configuration
import android.widget.Toast
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

@Composable
fun RegisterView(
    viewModel: RegisterViewModel = hiltViewModel(),
    navController: NavController
) {
    val nickname: String by viewModel.name.observeAsState("")
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
            RegisterHeader()

            NickInput(nickname, viewModel::onNameUpdate)

            Spacer(Modifier.height(16.dp))

            EmailInput(email, viewModel::onEmailUpdate)

            Spacer(Modifier.height(16.dp))

            PasswordInput(password, viewModel::onPasswordUpdate)

            Spacer(Modifier.height(16.dp))

            RegisterButton(viewModel::onRegister)

        }
    }
}

@Composable
private fun NickInput(value: String, onTextChange: (String) -> Unit) {
    OutlinedTextField(
        value = value,
        onValueChange = { onTextChange(it) },
        label = {
            Text(text = "Nick")
        },
        singleLine = true,
        modifier = Modifier
            .fillMaxWidth()
    )
}

@Composable
private fun RegisterButton(onClick: () -> Unit) {
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
        Text(text = "Register")
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
private fun RegisterHeader() {
    Text(
        text = "Register form",
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
private fun RegisterScreenPreview() {
    AppTheme {
        RegisterView(navController = rememberNavController())
    }
}