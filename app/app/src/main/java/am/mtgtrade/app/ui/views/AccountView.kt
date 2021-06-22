package am.mtgtrade.app.ui.views

import am.mtgtrade.app.ui.TopBar
import am.mtgtrade.app.ui.theme.AppTheme
import am.mtgtrade.app.viewmodels.AccountViewModel
import am.mtgtrade.app.viewmodels.CardInfoViewModel
import android.content.res.Configuration
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun AccountView(openDrawer: () -> Unit) {
    Column(modifier = Modifier.fillMaxSize()) {
        TopBar(
            title = "Account",
            buttonIcon = Icons.Filled.Menu,
            onButtonClicked = { openDrawer() }
        )
        AccountContent()
    }
}

@Composable
fun AccountContent(viewModel: AccountViewModel = hiltViewModel()) {
    val name: String by viewModel.name.observeAsState("")
    val email: String by viewModel.email.observeAsState("")
    val phone: String by viewModel.phone.observeAsState("")

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
            Text(text = "Name: $name")
            Text(text = "Email: $email")
            Text(text = "Phone Number: $phone")
            Spacer(modifier = Modifier.height(16.dp))
            //EditButton()
        }
    }
}

@Composable
fun TextWithInput(text: String, keyboard: KeyboardType) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Text(
            text = text.plus(":"),
            fontSize = 18.sp,
            modifier = Modifier
                .padding(horizontal = 8.dp)
        )
        OutlinedTextField(
            value = "",
            onValueChange = {},
            label = {
                Text(text = text)
            },
            singleLine = true,
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = keyboard
            ),
            modifier = Modifier
                .fillMaxWidth()
        )
    }
}

@Composable
fun EditButton() {
    Button(
        colors = ButtonDefaults.buttonColors(
            backgroundColor = MaterialTheme.colors.secondary
        ),
        shape = MaterialTheme.shapes.medium,
        onClick = {},
        modifier = Modifier
            .fillMaxWidth()
            .height(48.dp)
            .padding(horizontal = 16.dp)
    ) {
        Text(text = "Update Information")
    }
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
private fun AccountScreenPreview() {
    AppTheme {
        AccountContent()
    }
}