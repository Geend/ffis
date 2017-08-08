package net.torbenvoltmer.ffis.android

import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.app.TaskStackBuilder
import android.content.Context
import android.content.Intent
import android.os.IBinder
import android.support.v7.app.NotificationCompat
import android.util.Log
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.datatype.joda.JodaModule
import com.fasterxml.jackson.module.kotlin.KotlinModule

import com.google.firebase.messaging.FirebaseMessaging
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import net.torbenvoltmer.ffis.common.state.ConcreteState

class FfisFirebaseClient : FirebaseMessagingService() {


    override fun onMessageReceived(remoteMessage: RemoteMessage?) {

        if (remoteMessage!!.data.isNotEmpty()) {




            val mapper = ObjectMapper().registerModule(KotlinModule())
            mapper.registerModule(JodaModule())
            LocalStateManager.localFlyingState =  mapper.readValue(remoteMessage.data["data"], ConcreteState::class.java)


            val title:String
            val text:String

            if( LocalStateManager.localFlyingState.state) {
                title =  getString(R.string.flying);
                text = getString(R.string.flying_since,  LocalStateManager.localFlyingState.since.toString())
            }
            else {
                title = getString(R.string.not_fling);
                text = getString(R.string.not_flying_since,  LocalStateManager.localFlyingState.since.toString())
            }

            //TODO: Externalize notification (remove from firebase client)
            //TODO: Notification sound
            //TODO: No notification if app is visible
            //TODO: Remove notification when user opens app
            //TODO: User setting for disabling notifications
            val mBuilder = NotificationCompat.Builder(this)
                    .setSmallIcon(R.drawable.ic_stat_name)
                    .setContentTitle(title)
                    .setContentText(text)

            val resultIntent = Intent(this, MainActivity::class.java)


            val stackBuilder = TaskStackBuilder.create(this)
            stackBuilder.addParentStack(MainActivity::class.java)
            stackBuilder.addNextIntent(resultIntent)
            val resultPendingIntent = stackBuilder.getPendingIntent(
                    0,
                    PendingIntent.FLAG_UPDATE_CURRENT
            )
            mBuilder.setContentIntent(resultPendingIntent)
            val mNotificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

            mNotificationManager.notify(5, mBuilder.build())

        }


    }
}
