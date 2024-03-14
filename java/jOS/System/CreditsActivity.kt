package jOS.System

import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.browser.customtabs.CustomTabsIntent
import androidx.cardview.widget.CardView
import jOS.Core.ActionBar
import jOS.Core.ThemeEngine

class CreditsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(ThemeEngine.getSystemTheme(this))
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_credits)
        ActionBar.actionBarConfig(
            R.string.contributors_cloud_fragment_title,
            jOS.Core.R.drawable.ic_launcher_j,
            false,
            this,
            ""
        )
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

    override fun onResume() {
        super.onResume()
        ThemeEngine.relaunch(this)
    }
}