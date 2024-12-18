package jOS.System.Egg

import android.content.ComponentName
import android.content.Intent
import android.os.Bundle
import androidx.cardview.widget.CardView
import jOS.Core.jActivity
import jOS.System.Egg.androidI.*
import jOS.System.Egg.androidJ.*
import jOS.System.Egg.androidL.*
import jOS.System.Egg.androidN.neko.*
import jOS.System.R

class MenuActivity : jActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        configure(R.layout.activity_menu, false)
        super.onCreate(savedInstanceState)
        val ics = findViewById<CardView>(R.id.ics)
        val j = findViewById<CardView>(R.id.j)
        val k = findViewById<CardView>(R.id.k)
        val l = findViewById<CardView>(R.id.l)
        val n = findViewById<CardView>(R.id.n)
        val p = findViewById<CardView>(R.id.p)
        val q = findViewById<CardView>(R.id.q)

        ics?.setOnClickListener {
            startActivity(
                Intent(this@MenuActivity, Nyandroid::class.java)
                    .setFlags(
                        Intent.FLAG_ACTIVITY_NEW_TASK
                                or Intent.FLAG_ACTIVITY_CLEAR_TASK
                                or Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS
                    )
            )
        }

        j?.setOnClickListener {
            startActivity(
                Intent(this@MenuActivity, BeanBag::class.java)
                    .setFlags(
                        Intent.FLAG_ACTIVITY_NEW_TASK
                                or Intent.FLAG_ACTIVITY_CLEAR_TASK
                                or Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS
                    )
            )
        }

        k?.setOnClickListener {
            val intent = Intent(Intent.ACTION_MAIN)
            intent.component =
            ComponentName("com.android.systemui", "com.android.systemui.DessertCase")
            startActivity(intent)
        }

        l?.setOnClickListener {
            startActivity(
                Intent(this@MenuActivity, LLandActivity::class.java)
                    .setFlags(
                        Intent.FLAG_ACTIVITY_NEW_TASK
                                or Intent.FLAG_ACTIVITY_CLEAR_TASK
                                or Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS
                    )
            )
        }

        n?.setOnClickListener {
            startActivity(
                Intent(this@MenuActivity, NekoActivationActivity::class.java)
                    .setFlags(
                        Intent.FLAG_ACTIVITY_NEW_TASK
                                or Intent.FLAG_ACTIVITY_CLEAR_TASK
                                or Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS
                    )
            )
        }

        p?.setOnClickListener {
            val intent = Intent(Intent.ACTION_MAIN)
            intent.component =
                ComponentName("com.android.egg", "com.android.egg.paint.PaintActivity")
            startActivity(intent)
        }

        q?.setOnClickListener {
            val intent = Intent(Intent.ACTION_MAIN)
            intent.component =
                ComponentName("com.android.egg", "com.android.egg.quares.QuaresActivity")
            startActivity(intent)
        }
    }
}
