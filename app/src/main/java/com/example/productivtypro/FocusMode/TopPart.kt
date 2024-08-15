package com.example.productivtypro.FocusMode

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.productivtypro.HomePage.HomePage
import com.example.productivtypro.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopPart(navController:NavController){
    Row(modifier = Modifier
        .fillMaxWidth()
        .padding(10.dp), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Center) {
        // go to settings
        Column(modifier = Modifier.weight(1f), horizontalAlignment = Alignment.Start) {
            IconButton(onClick = {navController.navigate(HomePage.FocusSettings.name)}) {
                Icon(painter = painterResource(R.drawable.settings), contentDescription = "Focus Settings")
            }
            Text(stringResource(R.string.settings), fontSize = 10.sp)
        }

        // turn on white sound
        var expanded by remember { mutableStateOf(false) }

        ExposedDropdownMenuBox(expanded = expanded, onExpandedChange = {expanded = !expanded}) {
            val soundList = listOf(
                R.raw.white_noise to "White noise",
                R.raw.waterfall to "Waterfall",
                R.raw.rainfall to "Rain",
                R.raw.ocean to "Ocean")
            Column(modifier = Modifier.menuAnchor()) {
                IconButton(onClick = {}) {
                    Icon(painter = painterResource(R.drawable.sounds), contentDescription ="Focus sound")
                }
                Text(stringResource(R.string.focusSound), fontSize = 10.sp)
            }
            ExposedDropdownMenu(expanded = expanded, onDismissRequest = {expanded = false}, modifier = Modifier.width(250.dp)) {
                soundList.forEach{
                    DropdownMenuItem(text = { FocusSoundItem(musicId = it.first, name = it.second) },onClick = {})
                }
            }
        }
    }
}