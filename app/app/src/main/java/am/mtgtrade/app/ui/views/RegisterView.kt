package am.mtgtrade.app.ui.views

import am.mtgtrade.app.ui.theme.AppTheme
import android.content.res.Configuration
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview

import androidx.compose.ui.unit.dp

@Composable
fun RegisterView() {
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

            NickInput()

            Spacer(Modifier.height(16.dp))

            EmailInput()

            Spacer(Modifier.height(16.dp))

            PasswordInput()

            Spacer(Modifier.height(16.dp))

            RegisterButton()

        }
    }
}

@Composable
private fun NickInput() {
    OutlinedTextField(
        value = "",
        onValueChange = {},
        label = {
            Text(text = "Nick")
        },
        singleLine = true,
        modifier = Modifier
            .fillMaxWidth()
    )
}

@Composable
private fun RegisterButton() {
    Button(
        colors = ButtonDefaults.buttonColors(
            backgroundColor = MaterialTheme.colors.secondary
        ),
        shape = MaterialTheme.shapes.medium,
        onClick = {
            // nawigacja
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
private fun PasswordInput() {
    OutlinedTextField(
        value = "",
        onValueChange = {},
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
private fun EmailInput() {
    OutlinedTextField(
        value = "",
        onValueChange = {},
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
        RegisterView()
    }
}