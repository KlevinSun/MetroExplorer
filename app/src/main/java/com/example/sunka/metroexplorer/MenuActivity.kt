package com.example.sunka.metroexplorer


import android.content.Intent
import android.content.pm.PackageManager
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.util.Log
import com.example.sunka.metroexplorer.model.Metro

import kotlinx.android.synthetic.main.activity_menu.*


class MenuActivity : AppCompatActivity() {
    lateinit var wmataStationSearchManager: WmataStationSearchManager
    private var metroData = ArrayList<Metro>()
    private val REQUEST_CODE = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)


        select_station.setOnClickListener {
           // turn to MetroStation Activity when click
            val intent = Intent(this@MenuActivity, MetroStationActivity::class.java)

            startActivity(intent)
        }

        requestPermissionIfNecessary()
    }


    fun requestPermissionIfNecessary(){
        val selfPermission = ContextCompat.checkSelfPermission(this,
                android.Manifest.permission.ACCESS_FINE_LOCATION)
        if(selfPermission != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this,
                    arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION), REQUEST_CODE)
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if (requestCode == REQUEST_CODE){
            if(grantResults.isNotEmpty() && grantResults.first() != PackageManager.PERMISSION_GRANTED){
                requestPermissionIfNecessary()
            }
        }
    }


}
