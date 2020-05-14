package com.example.android.adhanapp

import android.media.MediaPlayer
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    private var mp: MediaPlayer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        DemoService.demarrerService(this)
        btnStop.setOnClickListener{
            lireAudio(R.raw.stop)
        }
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

}