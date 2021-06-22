package am.mtgtrade.app.ui.views.trade

import am.mtgtrade.app.ui.TopBar
import am.mtgtrade.app.ui.theme.AppTheme
import android.content.res.Configuration
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun AccountView(openDrawer: () -> Unit) {
    Column(modifier = Modifier.fillMaxSize()) {
        TopBar(
            title = "Trade",
            buttonIcon = Icons.Filled.Menu,
            onButtonClicked = { openDrawer() }
        )
        TradeContent()
    }
}

@Composable
private fun TradeContent() {
    Surface(
        color = MaterialTheme.colors.background,
        modifier = Modifier
            .fillMaxSize(),
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
        ) {
            TradeButton("Find offer")
            Spacer(modifier = Modifier.height(24.dp))
            TradeButton("Create offer")
            Spacer(modifier = Modifier.height(24.dp))
            TradeButton("My offers")
        }
    }
}

@Composable
private fun TradeButton(text: String) {
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
        Text(text = text)
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
        TradeContent()
    }
}