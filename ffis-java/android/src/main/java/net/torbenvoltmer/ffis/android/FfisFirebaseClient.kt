package net.torbenvoltmer.ffis.android

import android.app.NotificationManager
import android.app.PendingIntent
import android.app.TaskStackBuilder
import android.content.Context
import android.content.Intent
import android.support.v7.app.NotificationCompat
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.datatype.joda.JodaModule
import com.fasterxml.jackson.module.kotlin.KotlinModule

import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import net.torbenvoltmer.ffis.common.state.FalseState
import net.torbenvoltmer.ffis.common.state.StateVisitor
import net.torbenvoltmer.ffis.common.state.TrueState
import net.torbenvoltmer.ffis.common.state.UndefinedState
import net.torbenvoltmer.ffis.common.state.timedstate.ConcreteTimedState
import org.joda.time.format.DateTimeFormat

class FfisFirebaseClient : FirebaseMessagingService() {


    override fun onMessageReceived(remoteMessage: RemoteMessage?) {

        if (remoteMessage!!.data.isNotEmpty()) {
            
            LocalStateManager.localFlyingTimedState =  FfisJsonDecoder.timedState(remoteMessage.data["data"]!!)


            var title:String = ""
            var text:String = ""

            val fmt = DateTimeFormat.forPattern("HH:mm dd.MM.yyyy")
            val dtStr = fmt.print(LocalStateManager.localFlyingTimedState.since)

            LocalStateManager.localFlyingTimedState.state.accept(object : StateVisitor  {
                override fun handle(arg: TrueState) {
                    title =  getString(R.string.flying);
                    text = getString(R.string.flying_since,  dtStr)
                }

                override fun handle(arg: FalseState) {
                    title = getString(R.string.not_fling);
                    text = getString(R.string.not_flying_since,  dtStr)
                }

                override fun handle(arg: UndefinedState) {
                    title = getString(R.string.unknown_fling);
                    text = getString(R.string.unknown_flying_since,  dtStr)
                }
            })




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
