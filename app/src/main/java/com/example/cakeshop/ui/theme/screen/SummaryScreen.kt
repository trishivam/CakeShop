package com.example.cakeshop.ui.theme.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.cakeshop.R
import com.example.cakeshop.ui.theme.data.OrderUiState

@Composable
fun OrderSummaryScreen(
    orderUiState: OrderUiState,
    modifier: Modifier = Modifier
) {
    val resource = LocalContext.current.resources
    val numberOfCupcakes = resource.getQuantityString(
        R.plurals.cupcake,
        orderUiState.quantity,
        )

//    val orderSummary = stringResource(
//        R.string.orderDetails,
//        numberOfCupcakes,
//        orderUiState.flavor,
//        orderUiState.date,
//        orderUiState.quantity
//    )
    val newOrderOfCupcake = stringResource(R.string.newCupcakeOrder)
    val items = listOf(
        Pair( stringResource(R.string.quantity), numberOfCupcakes) ,
        Pair( stringResource(R.string.flavor), orderUiState.flavor ),
        Pair( stringResource(R.string.date), orderUiState.date )
    )

    Column (
        modifier = modifier,
        verticalArrangement = Arrangement.SpaceBetween
    ){
        Column (
            modifier = Modifier.padding(8.dp),
            verticalArrangement =   Arrangement.spacedBy(8.dp)
        ){
            items.forEach {
                item -> 
                Text( text = item.first.uppercase() )
                Text( text = item.second, fontWeight = FontWeight.Bold )
                Divider( thickness = 8.dp)
            }

        }
    }
}