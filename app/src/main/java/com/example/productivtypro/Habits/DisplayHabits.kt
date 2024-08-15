package com.example.productivtypro.Habits

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.productivtypro.R
import com.example.productivtypro.data.Habit.HabitData
import com.example.productivtypro.ui.theme.container_type_2_color

@Composable
fun DisplayHabits(habitList: List<HabitData>){

    if (habitList.isNotEmpty()){
        LazyColumn{
            items(items= habitList){
                HabitCard(habitData = it)
            }
        }
    }
    else{
        Column(Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally ) {
            Text(stringResource(id = R.string.nohabit))
            Text(stringResource(id = R.string.addnewhabits))
        }
    }
}

@Composable
fun HabitCard(habitData: HabitData){
    var expanded by remember{ mutableStateOf(false) }

    Card(modifier = Modifier
        .padding(3.dp)
        .fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = container_type_2_color)
        ){
        Column(modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth()
            .clickable(onClick = { expanded = !expanded })) {
            Row(verticalAlignment = Alignment.CenterVertically){
                Spacer(modifier = Modifier.height(20.dp))
                Text(habitData.name, fontSize = 20.sp, modifier = Modifier.weight(5f))
                Spacer(modifier = Modifier.height(60.dp))
            }
            if (expanded){
                DailyHabitContent(habitData = habitData)
            }
        }
    }
}