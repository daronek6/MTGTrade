package am.mtgtrade.app.ui.views.trade

import am.mtgtrade.app.R
import am.mtgtrade.app.ui.TopBar
import am.mtgtrade.app.ui.theme.AppTheme
import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun OfferInfoView(openDrawer: () -> Unit) {
    Column(modifier = Modifier.fillMaxSize()) {
        TopBar(
            title = "Offer Info",
            buttonIcon = Icons.Filled.Menu,
            onButtonClicked = { openDrawer() }
        )
        OfferInfoContent()
    }
}

@Composable
fun OfferInfoContent() {
    OfferInfo()
}

@Composable
private fun OfferInfo() {
    Card(
        shape = RoundedCornerShape(3.dp),
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Column {
            Text(
                text = "User: ".plus("From VM"),
                modifier = Modifier.padding(16.dp)
            )
            Text(
                text = "Card's name: ".plus("From VM"),
                modifier = Modifier.padding(16.dp)
            )
            Text(
                text = "Date: ".plus("From VM"),
                modifier = Modifier.padding(16.dp)
            )
            Text(
                text = "Email: ".plus("From VM"),
                modifier = Modifier.padding(16.dp)
            )
            Text(
                text = "Phone: ".plus("From VM"),
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
        OfferInfoContent()
    }
}