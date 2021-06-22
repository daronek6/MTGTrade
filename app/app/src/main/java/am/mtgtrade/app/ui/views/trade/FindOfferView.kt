package am.mtgtrade.app.ui.views.trade

import am.mtgtrade.app.R
import am.mtgtrade.app.ui.TopBar
import am.mtgtrade.app.ui.models.Offer
import am.mtgtrade.app.ui.theme.AppTheme
import am.mtgtrade.app.viewmodels.FindOfferViewModel
import android.content.ContentValues.TAG
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

@Composable
fun FindOfferView(openDrawer: () -> Unit, navController: NavController) {
    Column(modifier = Modifier.fillMaxSize()) {
        TopBar(
            title = "Find Offer",
            buttonIcon = Icons.Filled.Menu,
            onButtonClicked = { openDrawer() }
        )
        ScaffoldedContent(navController)
    }
}

@Composable
private fun ScaffoldedContent(navController: NavController) {
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = { /*do something*/ }) {
                Icon(painterResource(id = R.drawable.ic_baseline_photo_camera_24), contentDescription = "Turn on camera")
            }
        }
    ) {
        FindOfferContent(navController)
    }
}

@Composable
fun FindOfferContent(navController: NavController) {
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
            OffersColumn(navController)
        }
    }
}

@Composable
private fun SearchInput(viewModel: FindOfferViewModel = hiltViewModel()) {
    val search by viewModel.search.observeAsState("")
    OutlinedTextField(
        value = search,
        onValueChange = { viewModel.onSearchUpdate(it) },
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
                viewModel.onSearch()
                Log.e("*****", "SEEEEEARCH")
            }),
        modifier = Modifier
            .fillMaxWidth()
    )
}

@Composable
fun OffersColumn(navController: NavController, viewModel: FindOfferViewModel = hiltViewModel()) {
    val offers by viewModel.offers.observeAsState(listOf<Offer>())
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
    ) {
        offers.forEach {
            if(viewModel.isOfferFromCurrentUser(it) == false)
                Offer(it, navController)
        }
    }
}

@Composable
private fun Offer(offer: Offer, navController: NavController) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                navController.navigate("findOffer/${offer.id}")
             }
    ) {
        Column() {
            LineOfInfo(text = "Username: ${offer.userName}")
            LineOfInfo(text = "Card name: ${offer.cardName}")
            LineOfInfo(text = "Date: ${offer.date}")
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
        ScaffoldedContent(rememberNavController())
    }
}