package com.example.productivtypro.HomePage

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.productivtypro.HomePage.BottomBlocks.FocusTimerMode
import com.example.productivtypro.HomePage.BottomBlocks.HabitBlock
import com.example.productivtypro.HomePage.BottomBlocks.PlanMonthBlock
import com.example.productivtypro.HomePage.BottomBlocks.ProgressReportBlock
import com.example.productivtypro.R
import com.example.productivtypro.User.AccountName

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavHostController){
    Scaffold(topBar = {
        TopAppBar(colors = TopAppBarDefaults.topAppBarColors(Color.Transparent),
            title = { Row(verticalAlignment = Alignment.CenterVertically ){
            IconButton(onClick = {navController.navigate(HomePage.AccountSettings.name)},modifier = Modifier.weight(4f)) {
                Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Start, modifier = Modifier.fillMaxWidth() ){
                    Icon(painter = painterResource(R.drawable.account_circle),contentDescription = "Account Settings")
                    Spacer(modifier = Modifier.width(10.dp))
                    Text(AccountName(LocalContext.current).toString(), fontSize = 18.sp)
                }
            }
            IconButton(onClick = { TODO("Add option to Rate app") },modifier = Modifier.weight(1f)) {
                    Icon(painter = painterResource(R.drawable.reviews), contentDescription = "App Settings")
                }
            IconButton(onClick = { navController.navigate(HomePage.Settings.name)},modifier = Modifier.weight(1f)) {
                Icon(painter = painterResource(R.drawable.settings), contentDescription = "App Settings")
            }
            IconButton(onClick = { navController.navigate(HomePage.Analytics.name)},modifier = Modifier.weight(1f)) {
                Icon(painter = painterResource(R.drawable.analytics_icon), contentDescription = "Report")
            }
        }
        }, modifier = Modifier.height(50.dp))
    }, modifier = Modifier.padding(start = 6.dp,end = 6.dp)) {
    Column(modifier = Modifier
        .padding(it)
        .verticalScroll(rememberScrollState())) {
        // Alarm Toggle

        //Hello, User
        HelloGreeting(AccountName(LocalContext.current).toString() )
        Spacer(modifier = Modifier.height(10.dp))

        //Motivational Quotes
        Spacer(modifier = Modifier.height(10.dp))
        MotivationalQuotes()
        Spacer(modifier = Modifier.height(10.dp))

        //Today's Tasks
        Spacer(modifier = Modifier.height(10.dp))
        TodaysTasks()
        Spacer(modifier = Modifier.height(10.dp))

        FocusTimerMode(navController,modifier = Modifier
            .fillMaxWidth()
            .padding(3.dp))

        PlanMonthBlock(navController = navController,modifier = Modifier
            .fillMaxWidth()
            .padding(3.dp))


        HabitBlock(navController, modifier = Modifier
            .fillMaxWidth()
            .padding(3.dp))

        ProgressReportBlock(navController = navController,modifier = Modifier
            .fillMaxWidth()
            .padding(3.dp))

    }
}
}