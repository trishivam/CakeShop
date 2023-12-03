package com.example.cakeshop.ui.theme.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectable
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.cakeshop.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FlavorScreen(
    subTotal: String,
    options: List<String>,
    onSelectChanged: (String)-> Unit,
    modifier: Modifier = Modifier
) {
    var selectedValue by remember { mutableStateOf("") }
    
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column(
            modifier = modifier.padding(8.dp)
        ) {
            options.forEach {
                item ->
                Row (
                    modifier = Modifier.selectable(
                        selected = selectedValue == item,
                        onClick = {
                            selectedValue = item
                            onSelectChanged(item)
                        }
                    )
                ) {
                    RadioButton(
                        selected = selectedValue == item,
                        onClick = {
                            selectedValue = item
                            onSelectChanged(item)
                        })
                    Text(item)
                }
            }
            Divider(
                thickness = 8.dp,
                modifier = Modifier.padding(8.dp)
            )
            Text(
                text = stringResource(R.string.subtotal, subTotal),
                modifier = Modifier
                    .padding(8.dp)
                    .align(Alignment.End)
            )
        }
        Row (
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
                .weight(1f, false),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.Bottom
        ){
            OutlinedButton(
                modifier = Modifier.weight(1f),
                onClick = {  }) 
            {
                Text(text = "Cancel")
            }
            Button(
                modifier = Modifier.weight(1f),
                enabled = selectedValue.isNotEmpty(),
                onClick = { print( "Hello $selectedValue " )
                }
                
            ) {
                Text( text = "Next" )
            }
        }
    }
}
