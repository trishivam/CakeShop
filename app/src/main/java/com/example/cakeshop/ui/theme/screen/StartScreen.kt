package com.example.cakeshop.ui.theme.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHost
import androidx.navigation.NavHostController
import com.example.cakeshop.R

@Composable
fun StartScreen(
    onButtonClicked: (Int) -> Unit,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {

        Image(
            painter = painterResource(R.drawable.pexels_jess_bailey_designs_913136),
            contentDescription = "Jelly Cake",
            modifier = Modifier
                .padding(top = 10.dp)
                .size(height = 300.dp, width = 300.dp)

        )
        Text(
            modifier = Modifier.padding(start = 40.dp, bottom = 20.dp),
            text = "Order Cupcake",
            fontWeight = FontWeight.Bold,
            fontSize = 40.sp
        )

        Button(
            modifier = Modifier.padding(10.dp),
            onClick = { onButtonClicked(1) },

        ) {
            Text(text = stringResource(R.string.oneCupcake) )
        }
        Button(
            modifier = Modifier.padding(10.dp),

            onClick = { onButtonClicked(6) }
        ) {
            Text(text = "Six Cakecup")
        }
        Button(

            onClick = { onButtonClicked(12) }
        ) {
            Text(text = "Twelve Cakecup")
        }


    }

}