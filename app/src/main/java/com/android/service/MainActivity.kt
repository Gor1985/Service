package com.android.service

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.android.service.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private var id=1
    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.simpleService.setOnClickListener {
            startService(MyService.newIntent(this,30))
        }
        binding.foregroundService.setOnClickListener {
            showNotification()
        }
    }

    private fun showNotification() {
        val notificationManager=getSystemService(NOTIFICATION_SERVICE) as NotificationManager


        val notificationChannel=NotificationChannel(NOTIFICATION_CHANNEL_ID,
            CHANNEL_NAME,
            NotificationManager.IMPORTANCE_DEFAULT)
        notificationManager.createNotificationChannel(notificationChannel)


      val  notification=Notification.Builder(this, NOTIFICATION_CHANNEL_ID)
          .setContentTitle("Title")
          .setContentText("Text")
          .setSmallIcon(R.drawable.ic_launcher_background).build()

        notificationManager.notify(id++,notification)
    }
    companion object{
       private const val NOTIFICATION_CHANNEL_ID="channel_id"
        private const val CHANNEL_NAME="channel_name"
    }
}