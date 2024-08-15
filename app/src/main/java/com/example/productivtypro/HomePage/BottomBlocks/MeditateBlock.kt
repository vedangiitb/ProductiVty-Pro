package com.example.productivtypro.HomePage.BottomBlocks

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.productivtypro.HomePage.HomePage
import com.example.productivtypro.R

@Composable
fun MeditateBlock(navController: NavController,modifier: Modifier = Modifier){
    Card(modifier = modifier.clickable(onClick ={navController.navigate(HomePage.Meditate.name)} )){
        Column(horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.padding(5.dp)) {
            Image(painter = painterResource(R.drawable.meditate), contentDescription = "Meditate")
            Text(stringResource(R.string.meditate))
        }
    }
}