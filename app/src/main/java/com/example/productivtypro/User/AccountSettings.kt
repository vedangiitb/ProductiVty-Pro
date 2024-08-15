package com.example.productivtypro.User

import android.content.Context
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.productivtypro.R

@Composable
fun AccountSettings(navController:NavController){
    val context = LocalContext.current
    var userName = AccountName(context).toString()
    var newName by remember {
        mutableStateOf(userName)
    }

    Column(modifier= Modifier.fillMaxWidth()) {
        Spacer(modifier = Modifier.height(30.dp))

        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center){
            Text(stringResource(id = R.string.accountsettings))
        }
        
        Spacer(modifier = Modifier.height(30.dp))

        Text(stringResource(id = R.string.changeName))

        OutlinedTextField(value = newName, onValueChange = {newName = it})

        Button(onClick = {
            ChangeAccountName(context,newName)
            navController.popBackStack()}) {
            Text("Save my name")

        }
    }


}


fun AccountName(context: Context): String? {
    val preferences = context.getSharedPreferences("MyPreferences", Context.MODE_PRIVATE)
    return preferences.getString("username", "Guest")
}


fun ChangeAccountName(context: Context,newName:String){
    val preferences = context.getSharedPreferences("MyPreferences", Context.MODE_PRIVATE)
    preferences.edit().putString("username", newName).apply()
}