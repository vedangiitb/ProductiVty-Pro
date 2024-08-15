package com.example.productivtypro.FocusMode.focusTask

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.productivtypro.R
import com.example.productivtypro.ui.theme.container_type_2_color
import com.example.productivtypro.ui.theme.tilliumweb_extralight
import com.example.productivtypro.ui.theme.tilliumweb_extralight_it
import com.example.productivtypro.ui.theme.tilliumweb_light

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun IsCompletedDialog(onDismiss:()->Unit,markTaskDone:()->Unit){
    AlertDialog(onDismissRequest = onDismiss) {
        Card(modifier = Modifier.height(170.dp), colors = CardDefaults.cardColors(
            container_type_2_color
        )) {
            Column(modifier = Modifier.padding(20.dp)) {
                Text(text = stringResource(R.string.isover), fontSize = 20.sp, fontFamily = tilliumweb_extralight)
                Spacer(modifier = Modifier.height(10.dp))
                Text("This task will be marked as done",fontFamily = tilliumweb_extralight_it)
                Spacer(modifier = Modifier.height(20.dp))
                Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.End, modifier = Modifier.fillMaxWidth()) {
                    Text("No", modifier = Modifier.clickable(onClick = onDismiss),fontSize = 16.sp, fontFamily = tilliumweb_light, color = MaterialTheme.colorScheme.errorContainer)
                    Spacer(modifier = Modifier.width(10.dp))
                    Text( "Yes", modifier = Modifier.clickable(onClick = {
                        markTaskDone()
                        onDismiss()
                    }),fontSize = 16.sp, fontFamily = tilliumweb_light, color = MaterialTheme.colorScheme.errorContainer)
                }
            }
        }
    }
}