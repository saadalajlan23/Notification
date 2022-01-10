package com.example.notification

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private lateinit var button : Button
    private val channelId = "notifications"
    private val description = "Notification"
    private lateinit var ed1 : EditText
    lateinit var builder: Notification.Builder
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        ed1 = findViewById(R.id.edMessage)
        button = findViewById(R.id.button)

        button.setOnClickListener {
            createNotifications()
        }

    }

    private fun createNotifications() {
        val text = getMessage()
        val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        val intent = Intent(this, MainActivity::class.java)
        val pendingIntent =
            PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            var notificationChannel =
                NotificationChannel(channelId, description, NotificationManager.IMPORTANCE_HIGH)
            notificationManager.createNotificationChannel(notificationChannel)
            builder = Notification.Builder(this, channelId)
                .setSmallIcon(R.drawable.ic_baseline_notifications_24)
                .setContentIntent(pendingIntent)
                .setContentTitle("My Notification")
                .setContentText(text)
        } else {
            builder = Notification.Builder(this)
                .setSmallIcon(R.drawable.ic_baseline_notifications_24)
                .setContentIntent(pendingIntent)
                .setContentTitle("My Notification")
                .setContentText(text)
        }
        notificationManager.notify(1234, builder.build())

    }

    private fun getMessage(): String {
        return ed1.text.toString()
    }
}