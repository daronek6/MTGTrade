package am.mtgtrade.app.ui.views.trade

import am.mtgtrade.app.ui.TopBar
import am.mtgtrade.app.ui.models.Offer
import am.mtgtrade.app.ui.theme.AppTheme
import am.mtgtrade.app.viewmodels.MyOffersViewModel
import android.content.res.Configuration
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun MyOffersView(openDrawer: () -> Unit) {
    Column(modifier = Modifier.fillMaxSize()) {
        TopBar(
            title = "My Offers",
            buttonIcon = Icons.Filled.Menu,
            onButtonClicked = { openDrawer() }
        )
        MyOffersContent()
    }
}

@Composable
fun MyOffersContent(viewModel: MyOffersViewModel = hiltViewModel()) {
    val offers by viewModel.myOffers.observeAsState(listOf<Offer>())
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
            offers.forEach {
                Offer(it)
            }
        }
    }
}

@Composable
private fun Offer(offer: Offer, viewModel: MyOffersViewModel = hiltViewModel()) {
    Card() {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column() {
                LineOfInfo(text = "Username: ${offer.userName}")
                LineOfInfo(text = "Card name: ${offer.cardName}")
                LineOfInfo(text = "Date: ${offer.date}")
            }
            Column() {
                FloatingActionButton(onClick = { viewModel.removeOffer(offer.id!!) }) {
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
        text = text
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