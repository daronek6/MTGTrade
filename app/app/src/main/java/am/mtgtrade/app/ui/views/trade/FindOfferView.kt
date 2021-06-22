package am.mtgtrade.app.ui.views.trade

import am.mtgtrade.app.R
import am.mtgtrade.app.ui.TopBar
import am.mtgtrade.app.ui.theme.AppTheme
import android.content.res.Configuration
import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun FindOfferView(openDrawer: () -> Unit) {
    Column(modifier = Modifier.fillMaxSize()) {
        TopBar(
            title = "Find Offer",
            buttonIcon = Icons.Filled.Menu,
            onButtonClicked = { openDrawer() }
        )
        ScaffoldedContent()
    }
}

@Composable
private fun ScaffoldedContent() {
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = { /*do something*/ }) {
                Icon(painterResource(id = R.drawable.ic_baseline_photo_camera_24), contentDescription = "Turn on camera")
            }
        }
    ) {
        FindOfferContent()
    }
}

@Composable
fun FindOfferContent() {
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
            OffersLazyColumn()
        }
    }
}

@Composable
private fun SearchInput() {
    OutlinedTextField(
        value = "",
        onValueChange = {},
        label = {
            Text(text = "Find an offer")
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
fun OffersLazyColumn() {
    LazyColumn(
        horizontalAlignment = Alignment.CenterHorizontally,
        contentPadding = PaddingValues(vertical = 8.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
    ) {
        items(5) {
            Offer()
        }
    }
}

@Composable
private fun Offer() {
    Card(
        modifier = Modifier.clickable {  }
    ) {
        Column() {
            LineOfInfo(text = "Username: ")
            LineOfInfo(text = "Card name: ")
            LineOfInfo(text = "Date: ")
        }
    }
    Divider(color = Color.Blue, thickness = 1.dp)
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
        ScaffoldedContent()
    }
}