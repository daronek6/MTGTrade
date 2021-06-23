package am.mtgtrade.app.ui.views.trade

import am.mtgtrade.app.R
import am.mtgtrade.app.util.TakenPhoto
import am.mtgtrade.app.ui.TopBar
import am.mtgtrade.app.viewmodels.CreateOfferViewModel
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.google.accompanist.glide.rememberGlidePainter

@Composable
fun CreateOfferView(
    viewModel: CreateOfferViewModel = hiltViewModel(),
    openDrawer: () -> Unit,
    navController: NavController
) {
    Column(modifier = Modifier.fillMaxSize()) {
        TopBar(
            title = "Create Offer",
            buttonIcon = Icons.Filled.Menu,
            onButtonClicked = { openDrawer() }
        )
        ScaffoldedContent(viewModel, navController)
    }
}

@Composable
private fun ScaffoldedContent(
    viewModel: CreateOfferViewModel,
    navController: NavController
) {
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = { navController.navigate("camera") }) {
                Icon(painterResource(id = R.drawable.ic_baseline_photo_camera_24), contentDescription = "Turn on camera")
            }
        }
    ) {
        CreateOfferContent()
    }
}

@Composable
fun CreateOfferContent() {
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
            CardNameInput()
            Spacer(modifier = Modifier.height(16.dp))
            CardImage()
            Spacer(modifier = Modifier.height(16.dp))
            CreateOfferButton()
        }
    }
}

@Composable
private fun CardNameInput(viewModel: CreateOfferViewModel = hiltViewModel()) {
    val name by viewModel.name.observeAsState("")
    Row(verticalAlignment = Alignment.CenterVertically) {
        Text(
            text = "Card Name: ",
            fontSize = 18.sp,
            modifier = Modifier
                .padding(horizontal = 8.dp)
        )
        OutlinedTextField(
            value = name,
            onValueChange = {viewModel.onNameUpdate(it)},
            label = {
                Text(text = "Card Name")
            },
            singleLine = true,
            modifier = Modifier
                .fillMaxWidth()
        )
    }
}

@Composable
private fun CardImage(viewModel: CreateOfferViewModel = hiltViewModel()) {
    if (TakenPhoto.uri != null) {
        viewModel.readTextFromImage()
        Image(
            painter = rememberGlidePainter(TakenPhoto.uri),
            contentDescription = "Card's Photo",
            Modifier.fillMaxHeight(0.8f)
        )
    } else {
        Image(
            painter = painterResource(id = R.drawable.common_full_open_on_phone),
            contentDescription = "Card's photo",
            modifier = Modifier
                .fillMaxWidth()
                .height(480.dp)
        )
    }
}

@Composable
private fun CreateOfferButton(viewModel: CreateOfferViewModel = hiltViewModel()) {
    val context = LocalContext.current
    Button(
        colors = ButtonDefaults.buttonColors(
            backgroundColor = MaterialTheme.colors.secondary
        ),
        shape = MaterialTheme.shapes.medium,
        onClick = {
            val msg = viewModel.onCreateOffer()
            Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
                  },
        modifier = Modifier
            .fillMaxWidth()
            .height(48.dp)
            .padding(horizontal = 16.dp)
    ) {
        Text(text = "Create Offer")
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
//private fun AccountScreenPreview() {
//    AppTheme {
//        ScaffoldedContent()
//    }
//}