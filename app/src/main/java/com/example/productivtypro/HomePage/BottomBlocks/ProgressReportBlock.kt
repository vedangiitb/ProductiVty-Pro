package com.example.productivtypro.HomePage.BottomBlocks

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.productivtypro.HomePage.HomePage
import com.example.productivtypro.R
import com.example.productivtypro.ui.theme.container_type_1_color
import com.example.productivtypro.ui.theme.container_type_2_color
import com.example.productivtypro.ui.theme.tilliumweb_extralight
import com.example.productivtypro.ui.theme.tilliumweb_light

@Composable
fun ProgressReportBlock(navController: NavController,modifier: Modifier = Modifier){


    Card(modifier = modifier.clickable(onClick = {navController.navigate(HomePage.Analytics.name)}), colors = CardDefaults.cardColors(containerColor = container_type_2_color)){
        Row(modifier = Modifier.padding(5.dp)) {
            Image(painter = painterResource(id = R.drawable.progress_report), contentDescription = null,modifier = Modifier.weight(1f))
            Spacer(modifier = Modifier.width(20.dp))
            Column(modifier = Modifier.weight(2.5f)){
                Text(stringResource(R.string.progressreport),fontSize = 20.sp,fontFamily = tilliumweb_light)
                Text(stringResource(R.string.progressdescrip),fontSize = 14.sp,fontFamily = tilliumweb_extralight)
            }
        }
    }
}