package am.mtgtrade.app.ui.views.trade

import am.mtgtrade.app.R
import am.mtgtrade.app.ui.TopBar
import am.mtgtrade.app.ui.theme.AppTheme
import am.mtgtrade.app.ui.views.CardInfo
import am.mtgtrade.app.ui.views.ScaffoldedContent
import am.mtgtrade.app.ui.views.SearchInput
import android.content.res.Configuration
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.android.material.floatingactionbutton.FloatingActionButton

@Composable
fun MyOffersView(openDrawer: () -> Unit) {
    Column(modifier = Modifier.fillMaxSize()) {
        TopBar(
            title = "Card Info",
            buttonIcon = Icons.Filled.Menu,
            onButtonClicked = { openDrawer() }
        )
        MyOffersContent()
    }
}

@Composable
fun MyOffersContent() {
    Surface(
        color = MaterialTheme.colors.background,
        modifier = Modifier
            .fillMaxSize(),
    ) {
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
}

@Composable
fun Offer() {
    Card() {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column() {
                LineOfInfo(text = "Username: ")
                LineOfInfo(text = "Card name: ")
                LineOfInfo(text = "Date: ")
            }
            Column() {
                FloatingActionButton(onClick = { /*do something*/ }) {
                    Icon(Icons.Filled.Delete, contentDescription = "Delete offer")
                }
            }
        }
    }
    Divider(color = Color.Blue, thickness = 1.dp)
}

@Composable
fun LineOfInfo(text: String) {
    Text(
        text = text.plus("Info from vm") 
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
private fun CardInfoScreenPreview() {
    AppTheme {
        MyOffersContent()
    }
}