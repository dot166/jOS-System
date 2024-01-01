package jOS.System.Egg

import android.app.Activity
import android.content.ComponentName
import android.content.Intent
import android.os.Bundle
import androidx.cardview.widget.CardView

class MenuActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)
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
}
