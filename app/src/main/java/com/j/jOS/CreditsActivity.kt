package com.j.jOS

import android.app.Activity
import android.content.ComponentName
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.browser.customtabs.CustomTabsIntent
import androidx.cardview.widget.CardView
import com.android_i.egg.Nyandroid
import com.android_k.egg.DessertCase
import com.android_l.egg.LLandActivity
import com.j.jOS.R

class CreditsActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_credits)
        val lineage = findViewById<CardView>(R.id.lineage)
        val jf916 = findViewById<CardView>(R.id.jf916)
        val bh196 = findViewById<CardView>(R.id.bh196)

        lineage.setOnClickListener {
            val intent = Intent(Intent.ACTION_MAIN)
            intent.component =
                ComponentName("org.lineageos.lineageparts", "org.lineageos.lineageparts.contributors.ContributorsCloudFragment")
            startActivity(intent)
        }

        jf916.setOnClickListener {
            val url = "https://github.com/jf916"
            val intent: CustomTabsIntent = CustomTabsIntent.Builder()
                .build()
            intent.launchUrl(this@CreditsActivity, Uri.parse(url))
        }

        bh196.setOnClickListener {
            val url = "https://github.com/bh196"
            val intent: CustomTabsIntent = CustomTabsIntent.Builder()
                .build()
            intent.launchUrl(this@CreditsActivity, Uri.parse(url))
        }
    }
}