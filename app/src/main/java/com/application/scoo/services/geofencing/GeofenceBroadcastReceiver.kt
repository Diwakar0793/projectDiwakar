package com.application.scoo.services.geofencing

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

class GeofenceBroadcastReceiver : BroadcastReceiver() {
  override fun onReceive(context: Context, intent: Intent) {
    GeofenceTransitionsJobIntentService.enqueueWork(context, intent)
  }
}