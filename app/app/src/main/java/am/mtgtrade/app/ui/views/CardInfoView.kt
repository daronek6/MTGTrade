package am.mtgtrade.app.ui.views

import am.mtgtrade.app.ui.TopBar
import am.mtgtrade.app.viewmodels.LoginViewModel
import android.app.Activity
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun CardInfoView(openDrawer: () -> Unit, viewModel: LoginViewModel = hiltViewModel()) {

    Column(modifier = Modifier.fillMaxSize()) {
        TopBar(
            title = "Card Info",
            buttonIcon = Icons.Filled.Menu,
            onButtonClicked = { openDrawer() }
        )
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = "Card Info view content here.")
        }
    }

}