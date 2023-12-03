package com.example.cakeshop.ui.theme.screen

import android.icu.text.NumberFormat
import android.icu.text.SimpleDateFormat
import android.icu.util.ULocale
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.cakeshop.ui.theme.data.OrderUiState
import java.util.Calendar

private const val PRICE_PER_CUPCAKE = 2.00
private const val PRICE_FOR_SAME_DAY_PICKUP = 3.00


fun <T> MutableState<T>.update(updateBlock: (T) -> T) {
    val updatedState = updateBlock(this.value)
    this.value = updatedState
}
        
class OrderViewModel : ViewModel() {
//    private val _uiState = MutableStateFlow(OrderUiState(pickupOptions = pickupOptions()))
//    val uiState: StateFlow<OrderUiState> = _uiState.asStateFlow()
    val uiState = mutableStateOf(OrderUiState(pickupOptions = pickupOptions()))

    fun setQuantity(numberOfCupcakes: Int) {
        uiState.update { currentState ->
            currentState.copy(
                quantity = numberOfCupcakes,
                price = calculatePrice(quantity = numberOfCupcakes)
            )

        }
    }

    fun setFlavor(desiredFlavor: String){
        uiState.update { currentState -> currentState.copy(
            flavor = desiredFlavor
        )
        }
    }

    fun setDate(pickupDate: String) {
        uiState.update { currentState ->
            currentState.copy(
                date = pickupDate,
                price = calculatePrice(pickupDate = pickupDate)
            )
        }
    }

    fun resetOrder() {
        uiState.value = OrderUiState(pickupOptions = pickupOptions())
    }


    private fun calculatePrice(
        quantity: Int = uiState.value.quantity,
        pickupDate: String = uiState.value.date
    ): String {
        var calculatedPrice = quantity * PRICE_PER_CUPCAKE
        // If the user selected the first option (today) for pickup, add the surcharge
        if (pickupOptions()[0] == pickupDate) {
            calculatedPrice += PRICE_FOR_SAME_DAY_PICKUP
        }
        return NumberFormat.getCurrencyInstance().format(calculatedPrice)
    }

    fun pickupOptions(): List<String> {
        val dateOptions = mutableListOf<String>()
        val formatter = SimpleDateFormat("E MMM d", ULocale.getDefault())
        val calendar = Calendar.getInstance()
        // add current date and the following 3 dates.
        repeat(4) {
            dateOptions.add(formatter.format(calendar.time))
            calendar.add(Calendar.DATE, 1)
        }
        return dateOptions
    }

}