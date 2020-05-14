package com.example.android.adhanapp

import android.app.*
import android.content.Context
import android.content.Intent
import android.media.MediaPlayer
import java.util.*

class DemoService : IntentService("DemoService") {

    private var mp: MediaPlayer? = null

    override fun onHandleIntent(intent: Intent?) = try {
        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val notificationID = 1001
        val pIntent = PendingIntent.getService(this, System.currentTimeMillis().toInt(), intent!!, 0)

        val builder = builder(notificationManager, pIntent)
        var noti = builder!!.build()

        val adhan = AdhanTimes()
        val adhanTimes= arrayListOf<Int>(adhan.Fajr, adhan.Dohr , adhan.Asr,adhan.Maghrib, adhan.Isha)
        var cal = Calendar.getInstance()
        var hour =cal.get(Calendar.HOUR_OF_DAY)
        var minutes =cal.get(Calendar.MINUTE)
        var seconds = cal.get(Calendar.SECOND)
        var current_time = hour*3600+minutes*60+seconds

        while (!adhanTimes.contains(current_time)){
             cal = Calendar.getInstance()
             hour =cal.get(Calendar.HOUR_OF_DAY)
             minutes =cal.get(Calendar.MINUTE)
             seconds = cal.get(Calendar.SECOND)
             current_time = hour*3600+minutes*60+seconds

        }

        notificationManager.notify(notificationID, noti)

        lireAudio(R.raw.adhan)
        Thread.sleep(10000)
        lireAudio(R.raw.adhan)

    } catch (ex: Exception) {

    }

    fun lireAudio(resId: Int) {
        if (mp == null) {
            mp = MediaPlayer.create(this, resId)

            try {
                mp!!.prepare()

            } catch (e: Exception) {
                e.printStackTrace()
            }

        }
        if (!mp!!.isPlaying())
            mp!!.start()
        else
            mp!!.pause()
    }


    private fun builder(nm: NotificationManager, pi: PendingIntent): Notification.Builder? {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            val mChannel = NotificationChannel(
                "ch00", "ch00", NotificationManager.IMPORTANCE_HIGH)
            nm.createNotificationChannel(mChannel)
            val builder = Notification.Builder(this,"ch00")
                .setContentTitle("Temps Pour El Salat")
                .setContentText("El Salat apres 10 min").setSmallIcon(R.drawable.kaaba)
                .setContentIntent(pi).setAutoCancel(true)
                .setOngoing(true)
                .setAutoCancel(true)
            return builder


        }else{

            val builder = Notification.Builder(this)
                .setContentTitle("Service")
                .setContentText("Notification à partir d'un service").setSmallIcon(R.drawable.kaaba)
                .setContentIntent(pi).setAutoCancel(true)
                .setOngoing(true)
                .setAutoCancel(true)
            return builder

        }

    }

    /**
     * Handle action Foo in the provided background thread with the provided
     * parameters.
     */
    private fun handleActionFoo(param1: String, param2: String) {
        // TODO: Handle action Foo
        throw UnsupportedOperationException("Not yet implemented")
    }

    /**
     * Handle action Baz in the provided background thread with the provided
     * parameters.
     */
    private fun handleActionBaz(param1: String, param2: String) {
        // TODO: Handle action Baz
        throw UnsupportedOperationException("Not yet implemented")
    }

    companion object {
        private val ACTION_SERVICE = "dz.esi.demonotifsser"

        fun demarrerService(context: Context) {
            val intent = Intent(context, DemoService::class.java)
            intent.action = ACTION_SERVICE
            context.startService(intent)
        }
    }
}