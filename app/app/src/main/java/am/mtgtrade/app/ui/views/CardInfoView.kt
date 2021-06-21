package am.mtgtrade.app.ui.views

import am.mtgtrade.app.R
import am.mtgtrade.app.ui.TopBar
import am.mtgtrade.app.ui.theme.AppTheme
import android.content.res.Configuration
import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.ImeOptions
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun CardInfoView(openDrawer: () -> Unit) {
    Column(modifier = Modifier.fillMaxSize()) {
        TopBar(
            title = "Card Info",
            buttonIcon = Icons.Filled.Menu,
            onButtonClicked = { openDrawer() }
        )
        Scaffold(
            floatingActionButton = {
                FloatingActionButton(onClick = { /*do something*/ }) {
                    Icon(painterResource(id = R.drawable.ic_baseline_photo_camera_24), contentDescription = "Localized description")
                }
            }
        ) {
            CardInfoContent()
        }
    }
}

@Composable
fun CardInfoContent() {
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
            SearchInput()

            Spacer(Modifier.height(16.dp))

            CardInfo()

            Spacer(Modifier.height(16.dp))
        }
    }
}

@Composable
fun SearchInput() {
    OutlinedTextField(
        value = "",
        onValueChange = {},
        label = {
            Text(text = "Find a card")
        },
        leadingIcon = {
            Icon(
                Icons.Default.Search,
                contentDescription = null,
                modifier = Modifier
                    .size(18.dp)
            )
        },
        singleLine = true,
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Search
        ),
        keyboardActions = KeyboardActions(
            onSearch = {
            Log.e("*****", "SEEEEEARCH")
            }),
        modifier = Modifier
            .fillMaxWidth()
    )
}

@Composable
fun CardInfo() {
    Card(
        shape = RoundedCornerShape(3.dp),
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Text(
            text = "Card",
            modifier = Modifier.padding(16.dp)
        )
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
private fun CardInfoScreenPreview() {
    AppTheme {
        CardInfoContent()
    }
}