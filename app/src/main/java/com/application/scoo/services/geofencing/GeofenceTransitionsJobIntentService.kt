package com.application.scoo.services.geofencing

import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.core.app.JobIntentService
import com.google.android.gms.location.Geofence
import com.google.android.gms.location.GeofencingEvent
import android.R
import android.app.PendingIntent
import android.app.NotificationManager
import androidx.core.app.NotificationCompat
import com.application.scoo.MainActivity


class GeofenceTransitionsJobIntentService : JobIntentService() {

    companion object {
        private const val LOG_TAG = "GeoTrIntentService"

        private const val JOB_ID = 573

        fun enqueueWork(context: Context, intent: Intent) {
            enqueueWork(
                context,
                GeofenceTransitionsJobIntentService::class.java, JOB_ID,
                intent
            )
        }
    }

    override fun onHandleWork(intent: Intent) {
        val geofencingEvent = GeofencingEvent.fromIntent(intent)
        if (geofencingEvent.hasError()) {
            val errorMessage = GeofenceErrorMessages.getErrorString(
                this,
                geofencingEvent.errorCode
            )
            Log.e(LOG_TAG, errorMessage)
            return
        }

        handleEvent(geofencingEvent)
    }

    private fun handleEvent(event: GeofencingEvent) {
        when {
            event.geofenceTransition == Geofence.GEOFENCE_TRANSITION_ENTER -> showNotification("Entered", "Entered the Location")
            //send Notification
            event.geofenceTransition == Geofence.GEOFENCE_TRANSITION_EXIT -> showNotification("Exited", "Exited the Location")
            else -> showNotification("Error", "Error")
        }
    }

    fun showNotification(text: String, bigText: String) {

        // 1. Create a NotificationManager
        val notificationManager =
            this.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        // 2. Create a PendingIntent for AllGeofencesActivity
        val intent = Intent(this, MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP or Intent.FLAG_ACTIVITY_CLEAR_TOP)
        val pendingNotificationIntent =
            PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT)

        // 3. Create and send a notification
        val notification = NotificationCompat.Builder(this)
            .setSmallIcon(R.mipmap.sym_def_app_icon)
            .setContentTitle(text)
            .setContentText(text)
            .setContentIntent(pendingNotificationIntent)
            .setStyle(NotificationCompat.BigTextStyle().bigText(bigText))
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setAutoCancel(true)
            .build()
        notificationManager.notify(0, notification)
    }

}