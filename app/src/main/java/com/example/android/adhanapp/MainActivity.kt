package com.example.android.adhanapp

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import android.content.Context.ALARM_SERVICE
import android.support.v4.content.ContextCompat.getSystemService
import android.app.AlarmManager
import android.content.Context
import android.app.PendingIntent
import android.net.Uri
import java.util.*


class MainActivity : AppCompatActivity() {

    val CUSTOM_INTENT = "dz.esi.demobroadcast"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val adhan = AdhanTimes()
        // definir Adhan temps
        val AdhanList: Queue<Int> = LinkedList<Int>(listOf(adhan.Isha, adhan.Dohr , adhan.Asr,adhan.Maghrib, adhan.Isha))


        val context = this

        val alarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val notificationIntent = Intent(this, TestReceiver::class.java)
        notificationIntent.putExtra("Ringtone",
            Uri.parse("getResources().getResourceName(R.raw.ADHAN)"))
        val broadcast = PendingIntent.getBroadcast(this, 100, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT)

        val cal = Calendar.getInstance()
        val hour =cal.get(Calendar.HOUR_OF_DAY)
        System.out.print(hour)
        val minutes =cal.get(Calendar.MINUTE)
        System.out.print(minutes)
        val seconds = cal.get(Calendar.SECOND)
        var current_time = hour*3600+minutes*60+seconds
        var differenceTime = AdhanList.poll()-current_time
        System.out.print(differenceTime)
        cal.add(Calendar.SECOND, differenceTime)
        alarmManager.setExact(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(), broadcast);


    }
}