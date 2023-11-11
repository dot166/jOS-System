package com.j.jOS.egg.menu

import android.app.Activity
import android.content.ComponentName
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.browser.customtabs.CustomTabsIntent
import com.android_i.egg.Nyandroid
import com.android_k.egg.DessertCase
import com.android_l.egg.LLandActivity
import com.j.jOS.databinding.ActivityMenuBinding

class MenuActivity : Activity() {

    private lateinit var binding: ActivityMenuBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.ics.setOnClickListener { view ->
            startActivity(
                Intent(this@MenuActivity, Nyandroid::class.java)
                    .setFlags(
                        Intent.FLAG_ACTIVITY_NEW_TASK
                                or Intent.FLAG_ACTIVITY_CLEAR_TASK
                                or Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS
                    )
            )
        }

        binding.k.setOnClickListener { view ->
            startActivity(
                Intent(this@MenuActivity, DessertCase::class.java)
                    .setFlags(
                        Intent.FLAG_ACTIVITY_NEW_TASK
                                or Intent.FLAG_ACTIVITY_CLEAR_TASK
                                or Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS
                    )
            )
        }

        binding.l.setOnClickListener { view ->
            startActivity(
                Intent(this@MenuActivity, LLandActivity::class.java)
                    .setFlags(
                        Intent.FLAG_ACTIVITY_NEW_TASK
                                or Intent.FLAG_ACTIVITY_CLEAR_TASK
                                or Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS
                    )
            )
        }

        binding.p.setOnClickListener { view ->
            val intent = Intent(Intent.ACTION_MAIN)
            intent.component =
                ComponentName("com.android.egg", "com.android.egg.paint.PaintActivity")
            startActivity(intent)
        }

        binding.q.setOnClickListener { view ->
            val intent = Intent(Intent.ACTION_MAIN)
            intent.component =
                ComponentName("com.android.egg", "com.android.egg.quares.QuaresActivity")
            startActivity(intent)
        }

        binding.jf916.setOnClickListener { view ->
            val url = "https://github.com/jf916"
            val intent: CustomTabsIntent = CustomTabsIntent.Builder()
                .build()
            intent.launchUrl(applicationContext, Uri.parse(url))
        }

        binding.bh196.setOnClickListener { view ->
            val url = "https://github.com/bh196"
            val intent: CustomTabsIntent = CustomTabsIntent.Builder()
                .build()
            intent.launchUrl(applicationContext, Uri.parse(url))
        }
    }
}