package com.application.scoo

import android.content.Context
import android.graphics.*
import com.google.android.gms.maps.model.*
import kotlin.math.abs
import kotlin.math.atan

object MapUtils {

    fun getCarBitmap(context: Context): Bitmap {
        val bitmap = BitmapFactory.decodeResource(context.resources, R.drawable.bike_locator_icon)
        return Bitmap.createScaledBitmap(bitmap, 100, 100, false)
    }

    fun getOriginDestinationMarkerBitmap(): Bitmap {
        val height = 20
        val width = 35
        val bitmap = Bitmap.createBitmap(height, width, Bitmap.Config.RGB_565)
        val canvas = Canvas(bitmap)
        val paint = Paint()
        paint.color = Color.GRAY
        paint.style = Paint.Style.FILL
        paint.isAntiAlias = true
        canvas.drawRect(0.5F, 0.5F, width.toFloat(), height.toFloat(), paint)
        return bitmap
    }

    fun getRotation(start: LatLng, end: LatLng): Float {
        val latDifference: Double = abs(start.latitude - end.latitude)
        val lngDifference: Double = abs(start.longitude - end.longitude)
        var rotation = -1F
        when {
            start.latitude < end.latitude && start.longitude < end.longitude -> {
                rotation = Math.toDegrees(atan(lngDifference / latDifference)).toFloat()
            }
            start.latitude >= end.latitude && start.longitude < end.longitude -> {
                rotation = (90 - Math.toDegrees(atan(lngDifference / latDifference)) + 90).toFloat()
            }
            start.latitude >= end.latitude && start.longitude >= end.longitude -> {
                rotation = (Math.toDegrees(atan(lngDifference / latDifference)) + 180).toFloat()
            }
            start.latitude < end.latitude && start.longitude >= end.longitude -> {
                rotation =
                    (90 - Math.toDegrees(atan(lngDifference / latDifference)) + 270).toFloat()
            }
        }
        return rotation
    }

    /**
     * This function returns the list of locations of Vehicle during the trip from Origin to Destination
     */
    fun getListOfLocations(): ArrayList<LatLng> {
        val locationList = ArrayList<LatLng>()
        locationList.add(LatLng(12.937233992413423, 77.58380564806977))
        locationList.add(LatLng(12.944093358201407, 77.57970723289036))
        locationList.add(LatLng(12.933072273047625, 77.57717522770096))
        locationList.add(LatLng(12.92913036055384, 77.58440359060876))

        locationList.add(LatLng(12.941383217462432, 77.62389565035166))
        locationList.add(LatLng(12.934994324031704, 77.6249041608932))
        locationList.add(LatLng(12.94157143115355, 77.63061190140485))
        locationList.add(LatLng(12.938078998413104, 77.62408876939152))
        locationList.add(LatLng(12.940849944566086, 77.62501144924867))
        locationList.add(LatLng(12.939511529136425, 77.62563372171047))
        return locationList
    }

}