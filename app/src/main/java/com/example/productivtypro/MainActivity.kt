package com.example.productivtypro

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import androidx.work.BackoffPolicy
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import com.example.productivtypro.HomePage.ProductiVtyPro
import com.example.productivtypro.ui.theme.ProductiVtyProTheme
import com.example.productivtypro.workers.TaskRepeat
import kotlinx.coroutines.flow.first
import java.time.Duration
import java.time.LocalDateTime

class MainActivity : ComponentActivity() {

    val dataStore: DataStore<Preferences> by preferencesDataStore(name = "productivtypro")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            ProductiVtyProTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ProductiVtyPro()
                    LaunchedEffect(key1 = Unit){
                        val now = LocalDateTime.now()
                        val tomorrowMidnight = now.toLocalDate().plusDays(1).atStartOfDay()
                        val initialDelay = Duration.between(now, tomorrowMidnight)

                        val workRequest = PeriodicWorkRequestBuilder<TaskRepeat>(
                            repeatInterval = Duration.ofDays(1),
                            flexTimeInterval = Duration.ofMinutes(10)
                        ).setInitialDelay(initialDelay)
                            .setBackoffCriteria(
                            backoffPolicy = BackoffPolicy.LINEAR,
                            duration = Duration.ofMinutes(10)
                        ).build()

                        val workManager = WorkManager.getInstance(applicationContext)
                        workManager.enqueueUniquePeriodicWork(
                            "RepeatTasks",
                            ExistingPeriodicWorkPolicy.UPDATE,
                            workRequest)
                    }
                }
            }
        }
    }

    private suspend fun save(key:String,value:String){
        val dataStoreKey = stringPreferencesKey(key)
        dataStore.edit {productivtypro ->
            productivtypro[dataStoreKey] = value
        }
    }

    private suspend fun read(key:String): String?{
        val dataStoreKey = stringPreferencesKey(key)
        val preferences = dataStore.data.first()
        return preferences[dataStoreKey]
    }

}