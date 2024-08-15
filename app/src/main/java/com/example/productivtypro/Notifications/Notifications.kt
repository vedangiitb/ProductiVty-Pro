package com.example.productivtypro.Notifications

import android.annotation.SuppressLint
import android.app.AlarmManager
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Context.NOTIFICATION_SERVICE
import android.content.Intent
import android.os.Build
import android.provider.Settings
import android.util.Log
import androidx.core.app.NotificationCompat
import com.example.productivtypro.R
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale


@SuppressLint("ScheduleExactAlarm")
fun setNotification(context: Context, hour:Int, minute:Int, id:Int, name:String){
        val calendar: Calendar = Calendar.getInstance().apply {
            timeInMillis = System.currentTimeMillis()
            set(Calendar.HOUR_OF_DAY, hour)
            set(Calendar.MINUTE, minute)
            set(Calendar.SECOND, 0)
        }

        if (calendar.timeInMillis <= System.currentTimeMillis()) {
            calendar.add(Calendar.DAY_OF_YEAR, 1)
        }

        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val intent = Intent(context, Notification:: class.java)
        intent.putExtra("id",id)
        intent.putExtra("name",name)
        val lastUsedRequestCode = getLastUsedRequestCode(context)

        val pendingIntent = PendingIntent.getBroadcast(context,lastUsedRequestCode,intent,
            PendingIntent.FLAG_MUTABLE)

        val nextRequestCode = lastUsedRequestCode + 1
        saveNextRequestCode(context, nextRequestCode)

        alarmManager.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP,calendar.timeInMillis,pendingIntent)
}

fun getLastUsedRequestCode(context: Context): Int {
    val preferences = context.getSharedPreferences("MyPreferences", Context.MODE_PRIVATE)
    return preferences.getInt("lastRequestCode", 0)
}

fun saveNextRequestCode(context: Context, nextRequestCode: Int) {
    val preferences = context.getSharedPreferences("MyPreferences", Context.MODE_PRIVATE)
    preferences.edit().putInt("lastRequestCode", nextRequestCode).apply()
}

@SuppressLint("ScheduleExactAlarm")
fun setHabitNotification(context: Context, hour: Int, minute: Int, id: Int, name:String, endDate:String){
    val calendar: Calendar = Calendar.getInstance().apply {
        timeInMillis = System.currentTimeMillis()
        set(Calendar.HOUR_OF_DAY, hour)
        set(Calendar.MINUTE, minute)
        set(Calendar.SECOND, 0)
    }

    if (calendar.timeInMillis <= System.currentTimeMillis()) {
        calendar.add(Calendar.DAY_OF_YEAR, 1)
    }


    val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
    val intent = Intent(context, HabitNotification:: class.java)
    intent.putExtra("id",id)
    intent.putExtra("name",name)
    intent.putExtra("endDate",endDate)
    intent.putExtra("alarmTime",calendar.timeInMillis)
    val pendingIntent = PendingIntent.getBroadcast(context,generateRequestCode(),intent,
        PendingIntent.FLAG_MUTABLE)

    alarmManager.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP,calendar.timeInMillis,pendingIntent)
}

fun generateRequestCode(): Int {
    return System.currentTimeMillis().toInt()
}


class HabitNotification : BroadcastReceiver(){
    override fun onReceive(context: Context, intent: Intent?) {
        try {
            showNotification(context,intent?.getStringExtra("name")?:"Maintain Habit","Do Your Daily Habit ",intent?.getIntExtra("id",0)?:0)
            val endDate = intent?.getStringExtra("endDate")?:""
            if (isTodayBeforeGivenDate(endDate)){
                scheduleNextAlarm(context, intent?.getLongExtra("alarmTime",0)?:0, generateRequestCode())
            }
        }
        catch (e:Exception){
            Log.d("Receive Exception",e.printStackTrace().toString())
        }
    }

    private fun showNotification(context: Context,title:String,description:String,id:Int){
        val manager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val channelName = "message_chanel"
        val channelId = "message_id"

        if (Build.VERSION.SDK_INT>= Build.VERSION_CODES.O){
            val channel = NotificationChannel(channelId,channelName, NotificationManager.IMPORTANCE_HIGH)
            manager.createNotificationChannel(channel)
        }

        val builder = NotificationCompat.Builder(context,channelId)
            .setContentTitle(title)
            .setContentText(description)
            .setSmallIcon(R.drawable.icon)

        manager.notify(id,builder.build())
    }
}

fun isTodayBeforeGivenDate(givenDateString: String): Boolean {
    // Get the current date
    val currentDate = Calendar.getInstance()

    // Parse the given date string
    val dateFormat = SimpleDateFormat("dd-MM-yyyy", Locale.getDefault())
    val givenDate = Calendar.getInstance()
    givenDate.time = dateFormat.parse(givenDateString) ?: Date(0)

    // Compare dates
    return currentDate.before(givenDate)
}

@SuppressLint("ScheduleExactAlarm")
fun scheduleNextAlarm(context: Context, previousAlarmTime: Long, requestCode: Int) {
    val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager

    // Calculate the next alarm time (add one day to the previous alarm time)
    val nextAlarmTime = previousAlarmTime + (24 * 60 * 60 * 1000) // Add one day in milliseconds

    // Create an Intent for the BroadcastReceiver
    val intent = Intent(context, HabitNotification::class.java)

    // Create a PendingIntent to be triggered when the alarm goes off
    val pendingIntent = PendingIntent.getBroadcast(
        context,
        requestCode,
        intent,
        PendingIntent.FLAG_UPDATE_CURRENT
    )

    // Set up the alarm with the next alarm time
    alarmManager.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP,nextAlarmTime,pendingIntent)
}

class Notification: BroadcastReceiver(){
    override fun onReceive(context: Context, intent: Intent?) {
        try {
            showNotification(context,intent?.getStringExtra("name")?:"Task","Time to do Your Next Task!",intent?.getIntExtra("id",0)?:0)
        }
        catch (e:Exception){
            Log.d("Receive Exception",e.printStackTrace().toString())
        }
    }

    private fun showNotification(context: Context,title:String,description:String,id:Int){
        val manager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val channelName = "message_chanel"
        val channelId = "message_id"

        if (Build.VERSION.SDK_INT>= Build.VERSION_CODES.O){
            val channel = NotificationChannel(channelId,channelName, NotificationManager.IMPORTANCE_HIGH)
            manager.createNotificationChannel(channel)
        }

        val builder = NotificationCompat.Builder(context,channelId)
            .setContentTitle(title)
            .setContentText(description)
            .setSmallIcon(R.drawable.icon)

        manager.notify(id,builder.build())
    }
}

//fun isNotificationAccessGranted(context: Context): Boolean  {
//    // For SDK versions below Oreo (26), we can use NotificationManagerCompat
//    // For Oreo (26) and above, we need to use NotificationManager
//    val notificationManager =
//        context.getSystemService(NOTIFICATION_SERVICE) as NotificationManager
//
//    // Check if the notification channel for your app is enabled
//    val channel = notificationManager.getNotificationChannel("your_channel_id")
//
//    // Return true if the channel exists and is enabled
//    return channel != null && channel.importance != NotificationManager.IMPORTANCE_NONE
//}

fun requestNotificationAccess(context: Context) {
    val intent = Intent(Settings.ACTION_ALL_APPS_NOTIFICATION_SETTINGS)
    context.startActivity(intent)
}

