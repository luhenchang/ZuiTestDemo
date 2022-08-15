package com.example.zuitestdemo

import android.app.Notification
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.zuitestdemo.compat.NotificationCompatHelper

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
    private val IN_USE_NOTIFICATION_ID = Int.MAX_VALUE - 2

    override fun onPause() {
        super.onPause()
        val activityIntent = Intent(this,MainActivity::class.java)
        activityIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        var pendingActivityIntent: PendingIntent? = PendingIntent.getActivity(
            this, 0, activityIntent,
            PendingIntent.FLAG_ONE_SHOT or PendingIntent.FLAG_UPDATE_CURRENT
                    or PendingIntent.FLAG_IMMUTABLE
        )
        buildTimerInUseNotification(
            this, "还有一分钟倒计时", Notification.PRIORITY_DEFAULT,
            pendingActivityIntent, IN_USE_NOTIFICATION_ID, false
        )
    }
    private fun buildTimerInUseNotification(
        context: Context, text: String,
        priority: Int, pendingIntent: PendingIntent?,
        notificationId: Int, showTicker: Boolean
    ) {
        val builder: Notification.Builder =
            NotificationCompatHelper.getInstance(context).getBuilder()
                .setAutoCancel(false)
                .setDeleteIntent(pendingIntent)
                .setOngoing(true)
                .setPriority(priority)
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setCategory(Notification.CATEGORY_ALARM)
                .setGroup(notificationId.toString())
                .setContentIntent(pendingIntent)
        if (showTicker) {
            builder.setTicker(text)
        }
        builder.setContentTitle(text)
        (getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager).notify(notificationId, builder.build())
    }
}