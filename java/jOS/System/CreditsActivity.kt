package jOS.System

import android.net.Uri
import android.os.Bundle
import androidx.browser.customtabs.CustomTabsIntent
import androidx.cardview.widget.CardView
import jOS.Core.jActivity

class CreditsActivity : jActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        configure(R.string.contributors_cloud_fragment_title, R.layout.activity_credits, false)
        super.onCreate(savedInstanceState)
        val jf916 = findViewById<CardView>(R.id.jf916)
        val bh196 = findViewById<CardView>(R.id.bh196)
        val graphene = findViewById<CardView>(R.id.graphene)

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

        graphene.setOnClickListener {
            val url = "https://github.com/GrapheneOS"
            val intent: CustomTabsIntent = CustomTabsIntent.Builder()
                .build()
            intent.launchUrl(this@CreditsActivity, Uri.parse(url))
        }
    }
}