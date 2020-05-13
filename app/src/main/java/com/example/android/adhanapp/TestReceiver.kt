package com.example.android.adhanapp

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.media.AudioAttributes
import android.R.raw




class TestReceiver : BroadcastReceiver() {

    private val channelId = "channel-01"
    private val channelName = "SIL Channel"
    private val importance = NotificationManager.IMPORTANCE_HIGH

    override fun onReceive(context: Context, intent: Intent) {

        var titre = ""
        var contenu = ""
        if (intent.action === "dz.esi.demobroadcast") {
            titre = "Un titre"
            contenu = "Diffusion"
        } else {
            titre = "Temps Pour El Salat"
            contenu = "EL Salat apres 10 minutes"
        }
        val pIntent = PendingIntent.getActivity(context, System.currentTimeMillis().toInt(), intent, 0)
        val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager


        //val soundUri = Uri.parse()

        val audioAttributes = AudioAttributes.Builder()
            .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
            .setUsage(AudioAttributes.USAGE_ALARM)
            .build()



        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            val mChannel = NotificationChannel(
                channelId, channelName, importance)

            notificationManager.createNotificationChannel(mChannel)
            val noti = Notification.Builder(context,"ch00")
                .setContentTitle(titre)
                .setContentText(contenu).setSmallIcon(android.R.drawable.btn_star)
                .setContentIntent(pIntent).setAutoCancel(true)

                .build()
            notificationManager.notify(0, noti)

        }else{

            val noti = Notification.Builder(context)
                .setContentTitle(titre)
                .setContentText(contenu).setSmallIcon(android.R.drawable.btn_star)
                .setContentIntent(pIntent).setAutoCancel(true)

                .build()
            notificationManager.notify(0, noti)


        }






    }
}