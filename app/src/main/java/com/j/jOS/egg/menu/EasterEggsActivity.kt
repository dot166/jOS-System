package com.dede.android_eggs.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import by.kirich1409.viewbindingdelegate.viewBinding
import com.j.jOS.R
import com.com.j.jOS.databinding.ActivityEasterEggsBinding
import com.com.j.jOS.egg.menu.util.EdgeUtils
import com.j.jOS.egg.menu.util.ThemeUtils
import com.j.jOS.egg.menu.views.main.StartupPage

/**
 * Easter Egg Collection
 */
class EasterEggsActivity : AppCompatActivity(R.layout.activity_easter_eggs) {

    private val binding: ActivityEasterEggsBinding by viewBinding(ActivityEasterEggsBinding::bind)

    override fun onCreate(savedInstanceState: Bundle?) {
        ThemeUtils.tryApplyOLEDTheme(this)
        EdgeUtils.applyEdge(window)
        super.onCreate(savedInstanceState)

        setSupportActionBar(binding.toolbar)

        BackPressedHandler(this).register()

        //StartupPage.show(this)
    }

}
