package de.otto.ov.brain.easytouch

import android.app.Notification
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.os.StrictMode
import java.text.DateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policy)

        setContentView(R.layout.activity_main)
    }

    fun buttonClickScale(view: View) {
        startActivity(Intent(this, ScaleActivity::class.java))
    }

    fun createNotification(view: View) {


        // Prepare intent which is triggered if the
        // notification is selected
        val intent = Intent(this, NotificationReceiverActivity::class.java)
        val pIntent = PendingIntent.getActivity(this, System.currentTimeMillis().toInt(), intent, 0)

        // Build notification
        // Actions are just fake
        val notification = Notification.Builder(this)
                .setContentTitle("Awesome notification " + DateFormat.getDateInstance().format(Date()))
                .setContentText("Awesome notification triggered by you ").setSmallIcon(R.mipmap.ic_launcher)
                .setContentIntent(pIntent)
                .addAction(R.mipmap.ic_launcher, "More", pIntent)
                .build()
        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        // hide the notification after its selected
        notification.flags = notification.flags or Notification.FLAG_AUTO_CANCEL

        notificationManager.notify(0, notification)
    }
}
