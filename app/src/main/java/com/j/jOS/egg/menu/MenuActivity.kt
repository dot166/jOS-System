package com.j.jOS.egg.menu

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import com.android_i.egg.Nyandroid
import com.android_k.egg.DessertCase
import com.android_l.egg.LLandActivity
import com.android_n.egg.NPlatLogoActivity
import com.android_p.egg.paint.PaintActivity
import com.android_q.egg.quares.QuaresActivity
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

        binding.n.setOnClickListener { view ->
            startActivity(
                Intent(this@MenuActivity, NPlatLogoActivity::class.java)
                    .setFlags(
                        Intent.FLAG_ACTIVITY_NEW_TASK
                                or Intent.FLAG_ACTIVITY_CLEAR_TASK
                                or Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS
                    )
            )
        }

        binding.p.setOnClickListener { view ->
            startActivity(
                Intent(this@MenuActivity, PaintActivity::class.java)
                    .setFlags(
                        Intent.FLAG_ACTIVITY_NEW_TASK
                                or Intent.FLAG_ACTIVITY_CLEAR_TASK
                                or Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS
                    )
            )
        }

        binding.q.setOnClickListener { view ->
            startActivity(
                Intent(this@MenuActivity, QuaresActivity::class.java)
                    .setFlags(
                        Intent.FLAG_ACTIVITY_NEW_TASK
                                or Intent.FLAG_ACTIVITY_CLEAR_TASK
                                or Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS
                    )
            )
        }
    }
}