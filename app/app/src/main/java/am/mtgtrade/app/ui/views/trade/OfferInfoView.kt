package am.mtgtrade.app.ui.views.trade

import am.mtgtrade.app.R
import am.mtgtrade.app.ui.TopBar
import am.mtgtrade.app.ui.theme.AppTheme
import am.mtgtrade.app.viewmodels.OfferInfoViewModel
import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun OfferInfoView(openDrawer: () -> Unit, userId: String?) {
    Column(modifier = Modifier.fillMaxSize()) {
        TopBar(
            title = "Offer Info",
            buttonIcon = Icons.Filled.Menu,
            onButtonClicked = { openDrawer() }
        )
        OfferInfoContent(userId)
    }
}

@Composable
fun OfferInfoContent(userId: String?) {
    OfferInfo(userId)
}

@Composable
private fun OfferInfo(userId: String?, viewModel: OfferInfoViewModel = hiltViewModel()) {
    val offer by viewModel.offer.observeAsState(am.mtgtrade.app.ui.models.Offer())
    viewModel.onLoad(userId!!)
    Card(
        shape = RoundedCornerShape(3.dp),
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Column {
            Text(
                text = "User: ${offer.userName}",
                modifier = Modifier.padding(16.dp)
            )
            Text(
                text = "Card's name: ${offer.cardName}",
                modifier = Modifier.padding(16.dp)
            )
            Text(
                text = "Date: ${offer.date}",
                modifier = Modifier.padding(16.dp)
            )
            Text(
                text = "Email: ${offer.userEmail}",
                modifier = Modifier.padding(16.dp)
            )
            Text(
                text = "Phone: ${offer.userPhone}",
                modifier = Modifier.padding(16.dp)
            )
            Image(
                painter = painterResource(id = R.drawable.common_full_open_on_phone),
                contentDescription = "Card's photo",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp)
            )
        }
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
        OfferInfoContent("aaa")
    }
}