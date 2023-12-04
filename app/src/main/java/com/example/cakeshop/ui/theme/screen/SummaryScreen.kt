package com.example.cakeshop.ui.theme.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.cakeshop.R
import com.example.cakeshop.ui.theme.data.OrderUiState

@Composable
fun OrderSummaryScreen(
    orderUiState: OrderUiState,
    onCancelButtonClicked: () -> Unit,
    onSendButtonClicked:(String, String) -> Unit,
    modifier: Modifier = Modifier
) {
//    val resource = LocalContext.current.resources
//    val numberOfCupcakes = resource.getQuantityString(
//        R.plurals.cupcake,
//        orderUiState.quantity,
//        )

    val orderSummary = stringResource(
        R.string.orderDetails,
        orderUiState.price,
        orderUiState.flavor,
        orderUiState.date,
        orderUiState.quantity
    )
    val newOrderOfCupcake = stringResource(R.string.newCupcakeOrder)
    val items = listOf(
        Pair( stringResource(R.string.quantity), orderUiState.price) ,
        Pair( stringResource(R.string.flavor), orderUiState.flavor ),
        Pair( stringResource(R.string.date), orderUiState.date )
    )

    Column (
        modifier = modifier,
        verticalArrangement = Arrangement.SpaceBetween
    ){

        items.forEach {
                item ->
            Text( text = item.first.uppercase() )
            Text( text = item.second, fontWeight = FontWeight.Bold )
            Divider( thickness = 8.dp)
        }
        Spacer(modifier = modifier.height(8.dp))
        Text(
            text = stringResource(R.string.subtotal, orderUiState.price),
            modifier = Modifier
                .padding(8.dp)
                .align(Alignment.End)
        )

        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = { onSendButtonClicked(newOrderOfCupcake, orderSummary)}
        ) {
            Text( stringResource(R.string.send))
        }
        OutlinedButton(
            modifier = Modifier.fillMaxWidth(),
            onClick = { onCancelButtonClicked() }
        ) {
            Text(stringResource(R.string.cancel))
        }

    }
}