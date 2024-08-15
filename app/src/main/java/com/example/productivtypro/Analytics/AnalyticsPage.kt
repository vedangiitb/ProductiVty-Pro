package com.example.productivtypro.Analytics

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.productivtypro.HomePage.HomePage
import com.example.productivtypro.R
import com.example.productivtypro.ui.theme.container_type_2_color
import com.example.productivtypro.ui.theme.tilliumweb_bold
import com.example.productivtypro.ui.theme.tilliumweb_extralight

@Composable
fun AnalyticsPage(navController: NavController){
    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(5.dp)) {

        Text(stringResource(id = R.string.productivityreport), fontSize = 25.sp, fontFamily = tilliumweb_bold)

        AnalyticsCard(name = stringResource(id = R.string.taskanalytics),
            description = stringResource(id = R.string.taskanalyticsdescrip),
            onClick = { navController.navigate(HomePage.TaskAnalytics.name) }
            )
        AnalyticsCard(name = stringResource(id = R.string.focusanalytics),
            description = stringResource(id = R.string.focusanalyticsdescrip),
            onClick = { navController.navigate(HomePage.FocusAnalytics.name) }
        )
        AnalyticsCard(name = stringResource(id = R.string.habitanalytics),
            description = stringResource(id = R.string.habitanalyticsdescrip),
            onClick = { navController.navigate(HomePage.HabitAnalytics.name) }
        )
    }
}

@Composable
fun AnalyticsCard(name:String,description:String,onClick: ()-> Unit){
    Card(modifier= Modifier
        .clickable(onClick = onClick)
        .fillMaxWidth()
        .padding(4.dp), colors = CardDefaults.cardColors(container_type_2_color)){
        Column(modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)) {
            Text(text = name, fontSize = 22.sp, fontFamily = tilliumweb_extralight)
            Text(text = description)
        } 
    }
}