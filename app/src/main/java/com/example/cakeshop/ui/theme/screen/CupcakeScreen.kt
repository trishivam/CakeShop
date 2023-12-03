package com.example.cakeshop.ui.theme.screen

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.cakeshop.ui.theme.data.DataSource

enum class Routes() {
    Start,
    Flavor,
    Pickup,
    Summary
}


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CupcakeScreen(orderViewModel: OrderViewModel = viewModel()) {

    val title by remember { mutableStateOf("Cupcake") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text( text = title ) },
                colors = TopAppBarDefaults.mediumTopAppBarColors(containerColor = MaterialTheme.colorScheme.primaryContainer)
            ) }
    ) { it ->
        val navController  = rememberNavController()
        NavHost(
            navController = navController,
            startDestination = Routes.Start.name,
            modifier = Modifier.padding(it)
        ) {
            composable(
                route = Routes.Start.name
            ) {
                StartScreen(
                    onButtonClicked = {
                        navController.navigate(route = Routes.Flavor.name)
//                        println("You ordered $it Cupcakes")
                        orderViewModel.setQuantity(it)
                    }
                )
            }
            // Select Flavor Screen
            composable(
                route = Routes.Flavor.name
            ) {
                val context = LocalContext.current
                FlavorScreen(
                    subTotal = orderViewModel.uiState.value.price,
                    options = DataSource.flavors.map { id -> context.resources.getString(id) },
                    onSelectChanged = { orderViewModel.setFlavor(it) }
                )
            }

            // navigate to Pickup scree
            composable(
                route = Routes.Pickup.name
            ) {
                val context = LocalContext.current
                FlavorScreen(
                    subTotal = orderViewModel.uiState.value.price,
                    options = orderViewModel.uiState.value.pickupOptions,
                    onSelectChanged = { orderViewModel.setDate(it) }
                )
            }

            composable(
                route = Routes.Summary.name
            ){
                OrderSummaryScreen(
                    orderUiState = orderViewModel.uiState.value
                )
            }
        }
    }
}
