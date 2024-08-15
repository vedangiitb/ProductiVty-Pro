package com.example.productivtypro.FocusMode.timer

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.example.productivtypro.ui.theme.tilliumweb_extralight_it

@Composable
fun TimerSetting(
    onDismiss: () -> Unit,
    onTimerSet: (Int,Int) -> Unit,
) {
    var showTimerInput by remember { mutableStateOf(false) }
    Dialog(
        onDismissRequest = { onDismiss() },
        properties = DialogProperties(
            dismissOnBackPress = true,
            dismissOnClickOutside = true
        )
    ) {
        val focusSessions = mutableListOf(25,50,75,90)
        val focusBreaks = mutableListOf(0,1,2,3)
        val breakDuration = 5
        Card{
            Column(modifier = Modifier.padding(5.dp), horizontalAlignment = Alignment.CenterHorizontally) {
                Text("Choose the required focus session")
                for (i in focusSessions.indices){
                    Card(modifier = Modifier
                        .padding(7.dp)
                        .fillMaxWidth()
                        .clickable(onClick = { onTimerSet(focusSessions[i], 0) }),
                        colors = CardDefaults.cardColors(containerColor = Color.DarkGray)) {
                        Column(modifier = Modifier.padding(7.dp)) {
                            Text("Duration: ${focusSessions[i]} minutes", fontSize = 14.sp)
                            if (focusBreaks[i]>0){
                                Text("Contains ${focusBreaks[i]} breaks of $breakDuration minutes each", fontSize = 11.sp, fontFamily = tilliumweb_extralight_it )
                            }
                            else{
                                Text("Contains no breaks", fontSize = 11.sp, fontFamily = tilliumweb_extralight_it )
                            }
                        }
                    }
                }
                Button(onClick = {showTimerInput = true}){
                    Text("Custom Focus Session",fontSize = 14.sp)
                }
            }
        }
    }

    if (showTimerInput){
        InputTime(onDismiss = {showTimerInput = false},onTimerSet)
    }
}