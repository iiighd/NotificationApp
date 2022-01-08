package com.example.notificationapp

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat

class MainActivity : AppCompatActivity() {
    private val CHANNEL_ID = "channel_id_example_01"
    private val notificationId = 101
    lateinit var button : Button
    lateinit var edittext : EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        createNotificationChannel()
        button=findViewById(R.id.button)
        button.setOnClickListener {
            sendNotification()
        }
        edittext = findViewById(R.id.notificationET)

    }
    private fun createNotificationChannel(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            val name = "Notification Title"
            val descriptionText = "Notification Desvription"
            val importance : Int = NotificationManager.IMPORTANCE_DEFAULT
            val channel : NotificationChannel = NotificationChannel(CHANNEL_ID, name , importance).apply {
                description = descriptionText
            }
            val notificationManager: NotificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }
    private fun sendNotification(){
        val text = edittext.text.toString()
        val builder: NotificationCompat.Builder = NotificationCompat.Builder(this , CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentTitle("Example Title")
            .setContentText(" $text")
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
        with(NotificationManagerCompat.from(this)){
            notify(notificationId, builder.build())
        }
    }
}