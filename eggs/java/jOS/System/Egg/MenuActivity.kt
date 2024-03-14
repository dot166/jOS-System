package jOS.System.Egg

import android.content.ComponentName
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import jOS.Core.ActionBar
import jOS.Core.ThemeEngine

class MenuActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(ThemeEngine.getSystemTheme(this))
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)
        ActionBar.actionBarConfig(
            R.string.app_name,
            jOS.Core.R.drawable.ic_launcher_j,
            false,
            this,
            ""
        )
        val ics = findViewById<CardView>(R.id.ics)
        val k = findViewById<CardView>(R.id.k)
        val l = findViewById<CardView>(R.id.l)
        val p = findViewById<CardView>(R.id.p)
        val q = findViewById<CardView>(R.id.q)

        ics.setOnClickListener {
            startActivity(
                Intent(this@MenuActivity, Nyandroid::class.java)
                    .setFlags(
                        Intent.FLAG_ACTIVITY_NEW_TASK
                                or Intent.FLAG_ACTIVITY_CLEAR_TASK
                                or Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS
                    )
            )
        }

        k.setOnClickListener {
            startActivity(
                Intent(this@MenuActivity, DessertCase::class.java)
                    .setFlags(
                        Intent.FLAG_ACTIVITY_NEW_TASK
                                or Intent.FLAG_ACTIVITY_CLEAR_TASK
                                or Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS
                    )
            )
        }

        l.setOnClickListener {
            startActivity(
                Intent(this@MenuActivity, LLandActivity::class.java)
                    .setFlags(
                        Intent.FLAG_ACTIVITY_NEW_TASK
                                or Intent.FLAG_ACTIVITY_CLEAR_TASK
                                or Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS
                    )
            )
        }

        p.setOnClickListener {
            val intent = Intent(Intent.ACTION_MAIN)
            intent.component =
                ComponentName("com.android.egg", "com.android.egg.paint.PaintActivity")
            startActivity(intent)
        }

        q.setOnClickListener {
            val intent = Intent(Intent.ACTION_MAIN)
            intent.component =
                ComponentName("com.android.egg", "com.android.egg.quares.QuaresActivity")
            startActivity(intent)
        }
    }

    override fun onResume() {
        super.onResume()
        ThemeEngine.relaunch(this)
    }
}
