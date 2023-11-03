package com.j.jOS.info

import android.app.Activity
import android.content.ActivityNotFoundException
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import com.j.jOS.databinding.ActivityAboutBinding
import com.j.jOS.egg.PlatLogoActivity
//import lineageos.os.Build

class InfoActivity : Activity() {

    /**
     * Scoped to the lifecycle of the fragment's view (between onCreateView and onDestroyView)
     */
    private var _binding: ActivityAboutBinding? = null
    val binding: ActivityAboutBinding
        get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityAboutBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //val lineageos = Build.LINEAGEOS_VERSION
        //val substring = "beyond1lte"
        //val usingjOSdevice = lineageos.contains(substring)
        //if (usingjOSdevice) {
            //binding.textViewjOS.text = "jOS device"
        //}
        //binding.textViewLineage.text = lineageos

        val imageViewAppIcon = binding.imageViewAppIcon
        imageViewAppIcon.setOnLongClickListener(View.OnLongClickListener { v ->
            try {
                startActivity(
                    Intent(applicationContext, PlatLogoActivity::class.java)
                        .setFlags(
                            Intent.FLAG_ACTIVITY_NEW_TASK
                                    or Intent.FLAG_ACTIVITY_CLEAR_TASK
                                    or Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS
                        )
                )
            } catch (ex: ActivityNotFoundException) {
                Log.e("PlatLogoActivity", "Couldn't catch a break.")
            }
            true
        })
    }
}
