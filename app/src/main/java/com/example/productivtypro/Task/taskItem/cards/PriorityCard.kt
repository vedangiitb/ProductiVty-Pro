package com.example.productivtypro.Task.taskItem.cards

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.productivtypro.ViewModels.Task.TaskEditViewModel
import com.example.productivtypro.data.Tasks.TasksData
import com.example.productivtypro.ui.theme.tilliumweb_extralight
import com.example.productivtypro.ui.theme.tilliumweb_extralight_it

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PriorityCard(text:String, onClick:()-> Unit, iconImage: Painter, taskEditViewModel: TaskEditViewModel, taskItem: TasksData){
    val options = listOf("Low", "Medium", "High")

    var expanded by remember { mutableStateOf(false) }

    var priority by remember { mutableStateOf(taskItem.priority) }

    ExposedDropdownMenuBox(expanded = expanded, onExpandedChange = {expanded = !expanded}) {
        Card(modifier = Modifier
            .fillMaxWidth()
            .menuAnchor()
            .padding(top = 4.dp),shape = RectangleShape,
            colors = CardDefaults.cardColors(containerColor = Color(10,9,15,255))) {
            Row(verticalAlignment = Alignment.CenterVertically ,modifier = Modifier.padding(start = 10.dp, top = 20.dp, bottom = 10.dp)) {
                Icon(painter = iconImage,contentDescription = null)
                Spacer(modifier = Modifier.padding(5.dp))
                Row(modifier = Modifier
                    .fillMaxWidth()
                    .padding(end = 15.dp),horizontalArrangement = Arrangement.SpaceBetween){
                    Text(text ,fontSize = 18.sp,fontFamily = tilliumweb_extralight)
                    Text(priority,fontSize = 18.sp,fontFamily = tilliumweb_extralight_it)
                }

                ExposedDropdownMenu(expanded = expanded, onDismissRequest = {expanded = false}) {
                    options.forEach{selectionOption ->
                        DropdownMenuItem(text = { Text(selectionOption) }, onClick = {
                            priority = selectionOption
                            expanded = false
                            taskEditViewModel.updatePriority(taskItem.id,selectionOption)
                        })
                    }
                }
            }
        }
    }
}
