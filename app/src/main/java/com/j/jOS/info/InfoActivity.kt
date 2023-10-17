package com.j.jOS.info

import android.content.ContentValues
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.j.jOS.R
import lineageos.os.Build

class InfoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val lineageos = Build.LINEAGEOS_VERSION
        val substring = "beyond1lte"
        val usingjOSdevice = lineageos.contains(substring)
        Log.i(ContentValues.TAG, lineageos)
        Log.i(ContentValues.TAG, usingjOSdevice.toString())
        setContentView(R.layout.activity_about)
    }
}
