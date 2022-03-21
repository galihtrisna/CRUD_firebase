package com.galih.crudfirebase

import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.media.RingtoneManager
import androidx.core.app.NotificationCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class MyFireBaseNotification : FirebaseMessagingService(){
    override fun onMessageReceived(p0: RemoteMessage) {
        super.onMessageReceived(p0)
        val notificationIntent = PendingIntent.getActivity(this, 0,
            Intent(this, MainActivity::class.java), PendingIntent.FLAG_UPDATE_CURRENT)
        val notificationBuilder = NotificationCompat.Builder(this, "channel_id")
            .setContentTitle(p0?.notification?.title)
            .setContentIntent(notificationIntent)
            .setContentText(p0?.notification?.body)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setStyle(NotificationCompat.BigTextStyle())
            .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION))
            .setAutoCancel(true)

        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.notify(0, notificationBuilder.build())
    }
    override fun onNewToken(p0: String) {
        super.onNewToken(p0)
    }
}