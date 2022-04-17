package com.application.scoo.ui.home.map

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.application.scoo.AppBase
import com.application.scoo.R
import com.application.scoo.common.*
import com.application.scoo.data.model.NotificationRequest
import com.application.scoo.data.model.NotificationResponse
import com.application.scoo.data.model.NotificationResponseDetails
import com.application.scoo.databinding.ActivityMapsBinding
/*import com.example.boomconsumer.AppBase
import com.example.boomconsumer.R
import com.example.boomconsumer.common.*
import com.example.boomconsumer.data.model.NotificationRequest
import com.example.boomconsumer.data.model.NotificationResponse
import com.example.boomconsumer.data.model.NotificationResponseDetails
import com.example.boomconsumer.databinding.ActivityMapsBinding*/
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.CircleOptions
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import dagger.android.AndroidInjection
import javax.inject.Inject


class MapsActivity : AppCompatActivity(), View.OnClickListener, OnMapReadyCallback {
    private var _mapsBinding: ActivityMapsBinding? = null
    private val mapsBinding get() = _mapsBinding!!
    private lateinit var mMap: GoogleMap
    lateinit var mapFragment: SupportMapFragment
    private val GEOFENCE_LAT = 12.93342
    private val GEOFENCE_LONG = 77.58314
    private val GEOFENCE_RADIUS = 10000.00
    private lateinit var mapViewModel: MapViewModel
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory


    var stringLatLngArray: Array<String>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _mapsBinding = ActivityMapsBinding
            .inflate(layoutInflater)
        setContentView(mapsBinding.root)
        iniiData()
    }

    private fun iniiData() {
        AndroidInjection.inject(this)
        mapViewModel =
            ViewModelProvider(this, viewModelFactory).get(MapViewModel::class.java)


        val userId = AppBase.sharedPreference!!.getValueString(AppConstants.userId)
        val vehicleId = AppBase.sharedPreference!!.getValueString(AppConstants.vehicleId)

        val notificationRequest = NotificationRequest()
//        notificationRequest.vehicleId = 46
//        notificationRequest.userId  = 16
        notificationRequest.userId = userId?.toInt()
        if (vehicleId != null) {
//            notificationRequest.vehicleId = vehicleId.toInt()
            notificationRequest.vehicleId = vehicleId.toInt()
        }
            AppBase.sharedPreference?.getValueString(AppConstants.contact).toString()
        mapViewModel.vehicleNotification(notificationRequest)

       // val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        /*if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && !notificationManager.isNotificationPolicyAccessGranted) {
            val intent = Intent(Settings.ACTION_NOTIFICATION_POLICY_ACCESS_SETTINGS)
            startActivity(intent)
        } else {

        }*/

        mapViewModel.getvehNotification().observe(this,  Observer { onVehDataFetched(it) })

        val maps = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        maps.getMapAsync(this)
        // mapsBinding.backBtn.setOnClickListener(this)
    }

    private fun onVehDataFetched(viewState: ViewState<NotificationResponse>) {
        when (viewState) {
            is Loading -> setProgress(true)
            is NetworkError -> {
                setProgress(false)
                showError(viewState.message!!)
            }
            is Success -> {
                setProgress(false)

              var geopoints =   viewState.mData.data?.get(0)?.let { it.geoPoints?.let { it1 ->
                    AppBase.sharedPreference?.save(AppConstants.vehicleArray,
                        it1
                    )
                } }
                Log.i("geopoints", geopoints.toString())
                Toast.makeText(this, "Geo-Points Success", Toast.LENGTH_LONG).show()
                populateGeoFence(viewState.mData.data)

            }
        }
    }


    private fun populateGeoFence(data: List<NotificationResponseDetails>?) {
        getLatLonArray(data?.get(0)!!.geoPoints)
    }

    private fun getLatLonArray(string: String?) {
        val stringArray: Array<String> = string!!.split(";").toTypedArray()
        stringLatLngArray = stringArray
    }



    override fun onClick(p0: View?) {
        TODO("Not yet implemented")
    }

    override fun onMapReady(googleMap: GoogleMap) {

        mMap = googleMap

        Handler(Looper.getMainLooper()).postDelayed(object : Runnable {
            override fun run() {
                setupMapFence(mMap, stringLatLngArray)
            }
        },2000)
    }

    private fun setupMap(googleMap: GoogleMap?) {
        googleMap?.addCircle(
            CircleOptions()
                .center(LatLng(GEOFENCE_LAT, GEOFENCE_LONG))
                .radius(GEOFENCE_RADIUS)
                .strokeColor(ContextCompat.getColor(this, R.color.red))
                .strokeWidth(2F)
                .fillColor(ContextCompat.getColor(this, R.color.white))
        )
    }


    private fun setupMapFence(googleMap: GoogleMap?, stringArray: Array<String>?) {
        for (i in 0 until stringArray!!.size - 1) {
            val latlong = stringArray[i].split(",".toRegex()).toTypedArray()
            val latitude = latlong[0].dropLast(1).toDouble()
            val longitude = latlong[1].dropLast(1).toDouble()
            val lat_long = LatLng(latitude, longitude)
            // below lin is use to zoom our camera on map.
            mMap.animateCamera(CameraUpdateFactory.zoomTo(6.0f))

            // below line is use to move our camera to the specific location.
            mMap.moveCamera(CameraUpdateFactory.newLatLng(lat_long))


        }
    }

    fun showError(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    fun showProgress() {
//        changePasswordBinding.progressL.visibility = View.VISIBLE
    }

    fun hideProgress() {
//        changePasswordBinding.progressL.visibility = View.GONE
    }

    private fun setProgress(isLoading: Boolean) {
        if (isLoading) {
            showProgress()
        } else {
            hideProgress()
        }
    }
}