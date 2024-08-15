package com.example.productivtypro.HomePage

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.productivtypro.R
import com.example.productivtypro.data.getRandomQuote
import com.example.productivtypro.data.random
import com.example.productivtypro.ui.theme.tilliumweb_bold_it
import com.example.productivtypro.ui.theme.tilliumweb_extralight
import com.example.productivtypro.ui.theme.tilliumweb_extralight_it
import java.util.Calendar

fun getGreeting(userName:String): String {
    val calendar = Calendar.getInstance()
    val hourOfDay = calendar.get(Calendar.HOUR_OF_DAY)

    return when {
        hourOfDay in 0..11 -> "Good morning, ${userName}!"
        hourOfDay in 12..16 -> "Good afternoon, ${userName}!"
        hourOfDay in 17..23 -> "Good evening, ${userName}!"
        else -> "Hello" // This case is for any unexpected values
    }
}

@Composable
fun HelloGreeting(userName:String){
    Text(getGreeting(userName ), fontFamily = tilliumweb_extralight, fontSize = 26.sp,modifier = Modifier.padding(bottom = 16.dp, top = 16.dp))
}

@Composable
fun MotivationalQuotes(){
    val (quote,author) = getRandomQuote()
    val backgroundList = listOf(R.drawable.q_1,R.drawable.q_2,R.drawable.q_3,R.drawable.q_4,R.drawable.q_5,R.drawable.q_6,R.drawable.q_7)
    val randambackground = random.nextInt(backgroundList.size)
    Box(modifier = Modifier.padding(top = 5.dp, bottom = 5.dp)){
        Image(painter = painterResource(backgroundList[randambackground]), contentDescription = "Background for motivational quotes", modifier = Modifier
            .height(160.dp)
            .fillMaxWidth()
            .clip(MaterialTheme.shapes.large)
            .shadow(elevation = 8.dp)
            ,contentScale = ContentScale.Crop)
        Column(modifier = Modifier
            .padding(15.dp)
            .fillMaxWidth(), verticalArrangement = Arrangement.Center){
            Text(text = quote, fontFamily = tilliumweb_bold_it, fontSize = 21.sp, color = Color.White)
            Spacer(modifier = Modifier.height(15.dp))
            Text(text = author, textAlign = TextAlign.Right, modifier = Modifier.fillMaxWidth(), fontFamily = tilliumweb_extralight_it,fontSize = 18.sp)
        }
    }
}