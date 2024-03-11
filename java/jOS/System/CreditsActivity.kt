package jOS.System

import android.app.Activity
import android.content.ComponentName
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.browser.customtabs.CustomTabsIntent
import androidx.cardview.widget.CardView

class CreditsActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_credits)
        val jf916 = findViewById<CardView>(R.id.jf916)
        val bh196 = findViewById<CardView>(R.id.bh196)

        jf916.setOnClickListener {
            val url = "https://github.com/dot166"
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