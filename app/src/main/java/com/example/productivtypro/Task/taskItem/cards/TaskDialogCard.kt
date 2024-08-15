package com.example.productivtypro.Task.taskItem.cards

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.productivtypro.ui.theme.tilliumweb_extralight


@Composable
fun TaskDialogCard(text:String,onClick:()-> Unit,iconImage: Painter){
    Card(modifier = Modifier
        .fillMaxWidth()
        .padding(top = 4.dp)
        .clickable(onClick = onClick),shape = RectangleShape,
        colors = CardDefaults.cardColors(containerColor = Color(10,9,15,255))) {
        Row(verticalAlignment = Alignment.CenterVertically ,modifier = Modifier.padding(start = 10.dp, top = 20.dp, bottom = 10.dp)) {
            Icon(painter = iconImage,contentDescription = null)
            Spacer(modifier = Modifier.padding(5.dp))
            Text(text,fontSize = 18.sp,fontFamily = tilliumweb_extralight)
        }
    }
}