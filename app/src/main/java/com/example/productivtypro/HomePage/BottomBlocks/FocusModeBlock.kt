package com.example.productivtypro.HomePage.BottomBlocks

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.productivtypro.HomePage.HomePage
import com.example.productivtypro.R
import com.example.productivtypro.ui.theme.container_type_2_color
import com.example.productivtypro.ui.theme.tilliumweb_extralight
import com.example.productivtypro.ui.theme.tilliumweb_light

@Composable
fun FocusTimerMode(navController: NavController,modifier: Modifier = Modifier){
    Card(modifier = modifier
        .clickable(onClick = { navController.navigate(HomePage.FocusSession.name) }),
        colors = CardDefaults.cardColors(containerColor =  container_type_2_color)) {
        Column(modifier = Modifier.padding(8.dp), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally ) {
            Image(painter = painterResource(R.drawable.focus_mode), contentDescription =null )
            Column(verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally ) {
                Text("Start Focus Mode", fontSize = 25.sp,fontFamily = tilliumweb_light)
                Text("Avoid distractions and focus to improve your productivity", textAlign = TextAlign.Center,fontFamily = tilliumweb_extralight )
            }
        }
    }
}