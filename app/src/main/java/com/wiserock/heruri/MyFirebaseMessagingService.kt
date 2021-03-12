package com.wiserock.heruri

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.media.RingtoneManager
import android.os.Build
import androidx.core.app.NotificationCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.wiserock.heruri.model.push.Push
import com.wiserock.heruri.utils.MyApp
import com.wiserock.heruri.utils.interfaces.TimeStamp
import com.wiserock.heruri.view.adapter.CourseAdapter
import com.wiserock.heruri.view.adapter.PushAdapter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.util.*

class MyFirebaseMessagingService : FirebaseMessagingService(), TimeStamp {
    override fun onNewToken(p0: String) {
        super.onNewToken(p0)
        println("newToken = ${p0}")
    }

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        super.onMessageReceived(remoteMessage)
        if (remoteMessage.notification != null) {
            println("p0.notification.body = ${remoteMessage.notification!!.body}")
            val messageBody = remoteMessage.notification!!.body
            val messageTitle = remoteMessage.notification!!.title
            println("messageBody = ${messageBody}")
            println("messageTitle = ${messageTitle}")
            val push = Push(
                name = messageTitle!!,
                time = Date().time,
                description = messageBody!!
            )
            GlobalScope.launch(Dispatchers.Main) {
                GlobalScope.launch(Dispatchers.IO) {
                    val appDatabase = MainActivity.appDatabase
                    val pushDAO = appDatabase.pushDAO()
                    pushDAO.insert(push)
                }
                MyApp.pushArrayList.add(push)
                CourseAdapter.viewModel.pushList.value = MyApp.pushArrayList
                PushAdapter.itemSize = MyApp.pushArrayList.size
            }
            val intent = Intent(this, MainActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            val pendingIntent =
                PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_ONE_SHOT)
            val channelId = "CM"
            val defaultSoundUrl = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
            val notificationBuilder: NotificationCompat.Builder =
                NotificationCompat.Builder(this, channelId)
                    .setSmallIcon(R.mipmap.ic_launcher)
                    .setContentTitle(messageTitle)
                    .setContentText(messageBody)
                    .setAutoCancel(true)
                    .setSound(defaultSoundUrl)
                    .setVibrate(longArrayOf(100, 200, 100, 200))
                    .setContentIntent(pendingIntent)
            val notificationManager: NotificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                val channelName = "Cloud Message"
                val channel =
                    NotificationChannel(channelId, channelName, NotificationManager.IMPORTANCE_HIGH)
                notificationManager.createNotificationChannel(channel)
            }
            notificationManager.notify(0, notificationBuilder.build())
        }
    }
}