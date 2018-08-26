package com.smile.boda.ui.activity

import android.graphics.Color
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.JointType
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.gms.maps.model.PolylineOptions
import com.smile.boda.R
import com.smile.boda.model.CoordinateModel
import com.google.android.gms.maps.model.Polyline
import com.google.android.gms.maps.model.PolygonOptions


class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private lateinit var origin: CoordinateModel
    private lateinit var dest: CoordinateModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)
        origin = intent.getParcelableExtra("origin")
        dest = intent.getParcelableExtra("dest")
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
                .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        val originAirport = LatLng(origin.latitude, origin.longitude)
        val destAirport = LatLng(dest.latitude, dest.longitude)
        mMap.addPolyline(PolylineOptions()
                .add(originAirport, destAirport))
        val line = mMap.addPolyline(PolylineOptions()
                .add(originAirport, destAirport)
                .width(5f)
                .color(Color.BLACK))
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(originAirport, mMap.minZoomLevel))

    }
}
