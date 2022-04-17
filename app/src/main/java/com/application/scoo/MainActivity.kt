package com.application.scoo

import  androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       setContentView(R.layout.fragment_signup)
      //  setContentView(R.layout.activity_main)

//        val dashBoardFragment = DashBoardFragment()
//        supportFragmentManager.beginTransaction().replace(R.id.fragment_container, dashBoardFragment).commit()
    }


}