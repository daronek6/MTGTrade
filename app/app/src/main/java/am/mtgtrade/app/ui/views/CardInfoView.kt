package am.mtgtrade.app.ui.views

import am.mtgtrade.app.R
import am.mtgtrade.app.TakenPhoto
import am.mtgtrade.app.ui.TopBar
import am.mtgtrade.app.viewmodels.CardInfoViewModel
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.compose.runtime.livedata.observeAsState
import androidx.navigation.NavController
import com.google.accompanist.glide.rememberGlidePainter

@Composable
fun CardInfoView(
    viewModel: CardInfoViewModel = hiltViewModel(),
    openDrawer: () -> Unit,
    navController: NavController
) {
    Column(modifier = Modifier.fillMaxSize()) {
        TopBar(
            title = "Card Info",
            buttonIcon = Icons.Filled.Menu,
            onButtonClicked = { openDrawer() }
        )
        ScaffoldedContent(navController, viewModel)
    }
}

@Composable
private fun ScaffoldedContent(navController: NavController, viewModel: CardInfoViewModel) {
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = { navController.navigate("camera") }) {
                Icon(painterResource(id = R.drawable.ic_baseline_photo_camera_24), contentDescription = "Turn on camera")
            }
        }
    ) {
        CardInfoContent(viewModel)
    }
}

@Composable
fun CardInfoContent(viewModel: CardInfoViewModel) {
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

            if (TakenPhoto.uri != null) {
                Image(
                    painter = rememberGlidePainter(TakenPhoto.uri),
                    contentDescription = "Card's Photo"
                )
            }
        }
    }
}

@Composable
private fun SearchInput(viewModel:CardInfoViewModel = hiltViewModel()) {
    val search: String by viewModel.search.observeAsState("")

    OutlinedTextField(
        value = search,
        onValueChange = { viewModel.onSearchUpdate(it) },
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
                viewModel.onSearch()
                Log.e("*****", "SEEEEEARCH")
            }),
        modifier = Modifier
            .fillMaxWidth()
    )
}

@Composable
fun CardInfo(viewModel: CardInfoViewModel = hiltViewModel()) {
    val cardName: String by viewModel.name.observeAsState("")
    val rarity: String by viewModel.rarity.observeAsState("")
    val setName: String by viewModel.setName.observeAsState("")

    Column {
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

        Card(
            shape = RoundedCornerShape(3.dp),
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Text(
                text = "Nazwa: $cardName",
                modifier = Modifier.padding(16.dp)
            )
        }

        Card(
            shape = RoundedCornerShape(3.dp),
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Text(
                text = "Rzadkość: $rarity",
                modifier = Modifier.padding(16.dp)
            )
        }

        Card(
            shape = RoundedCornerShape(3.dp),
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Text(
                text = "Kolekcja: $setName",
                modifier = Modifier.padding(16.dp)
            )
        }
    }

}

//@Preview(
//    name = "Night Mode",
//    uiMode = Configuration.UI_MODE_NIGHT_YES,
//)
//@Preview(
//    name = "Day Mode",
//    uiMode = Configuration.UI_MODE_NIGHT_NO,
//)
//@Composable
//private fun CardInfoScreenPreview() {
//    AppTheme {
//        ScaffoldedContent(navController = rememberNavController())
//    }
//}