package com.example.productivtypro.FocusMode.timer

import android.view.Gravity
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.compose.ui.window.DialogWindowProvider

@Composable
fun InputTime(onDismiss: () -> Unit,onTimerSet: (Int, Int) -> Unit){
    Dialog(onDismissRequest = { onDismiss() },properties = DialogProperties(
        dismissOnBackPress = true,
        dismissOnClickOutside = true
    )
    ) {
        val dialogWindowProvider = LocalView.current.parent as DialogWindowProvider
        dialogWindowProvider.window.setGravity(Gravity.BOTTOM)
        var minutes by remember {
            mutableStateOf(25)
        }

        var breaks by remember {
            mutableStateOf(0)
        }

        var breakDuration by remember {
            mutableStateOf(5)
        }

        Card(modifier = Modifier.fillMaxWidth()){
            Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp)) {
                Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp)){
                    Column(verticalArrangement = Arrangement.Center,horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.weight(2f)) {
                        Text(minutes.toString(), fontSize = 25.sp)
                        Text("Minutes")
                    }
                    Column {
                        IconButton(onClick = { minutes += 5 }) {
                            Icon(imageVector = Icons.Default.KeyboardArrowUp, contentDescription = null)
                        }
                        IconButton(onClick = { minutes = maxOf(minutes - 5, 1) }) {
                            Icon(imageVector = Icons.Default.KeyboardArrowDown,contentDescription = null)
                        }
                    }
                }

                Row(verticalAlignment = Alignment.CenterVertically){
                    IconButton(onClick = {breaks=maxOf(breaks-1,0)}) {
                        Icon(imageVector = Icons.Default.KeyboardArrowDown,contentDescription = null)
                    }
                    Text("$breaks breaks", fontSize = 14.sp)
                    IconButton(onClick = {breaks=minOf(breaks+1,minutes/25)}) {
                        Icon(imageVector = Icons.Default.KeyboardArrowUp,contentDescription = null)
                    }

                    Text("Of",fontSize = 14.sp)

                    IconButton(onClick = {breakDuration=maxOf(breakDuration-1,0)}) {
                        Icon(imageVector = Icons.Default.KeyboardArrowDown,contentDescription = null)
                    }
                    Text("$breakDuration mins",fontSize = 14.sp)
                    IconButton(onClick = {breakDuration=minOf(breakDuration+1,minutes/5)}) {
                        Icon(imageVector = Icons.Default.KeyboardArrowUp,contentDescription = null)
                    }
                }
                Button(onClick = {onTimerSet(minutes,0)}) {
                    Text("Done")
                }
            }
        }
    }
}

fun Long.formatTime(): String {
    val minutes = this / 60
    val remainingSeconds = this % 60
    return String.format("%02d:%02d",minutes, remainingSeconds)
}