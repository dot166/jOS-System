package com.j.jOS.info

import android.content.ActivityNotFoundException
import android.content.ContentValues
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.j.jOS.egg.PlatLogoActivity
import com.dede.basic.getLong
import com.dede.basic.putLong
import com.j.jOS.databinding.FragmentAboutBinding
import lineageos.os.Build

/**
 * Created by sds100 on 05/04/2020.
 */

class AboutFragment : Fragment() {

    /**
     * Scoped to the lifecycle of the fragment's view (between onCreateView and onDestroyView)
     */
    private var _binding: FragmentAboutBinding? = null
    val binding: FragmentAboutBinding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAboutBinding.inflate(inflater, container, false).apply {
            lifecycleOwner = viewLifecycleOwner
        }

        val lineageos = Build.LINEAGEOS_VERSION
        val substring = "beyond1lte"
        val usingjOSdevice = lineageos.contains(substring)
        if (usingjOSdevice) {
            binding.textViewjOS.text = "jOS device"
        }
            binding.textViewLineage.text = lineageos

        val imageViewAppIcon = binding.imageViewAppIcon
        imageViewAppIcon.setOnLongClickListener(View.OnLongClickListener { v ->
            if (v.context.getLong("k_egg_mode", 0)
                == 0L
            ) {
                // For posterity: the moment this user unlocked the easter egg
                v.context.putLong("k_egg_mode", System.currentTimeMillis())
            }
            try {
                startActivity(
                    Intent(context, PlatLogoActivity::class.java)
                        .setFlags(
                            Intent.FLAG_ACTIVITY_NEW_TASK
                                    or Intent.FLAG_ACTIVITY_CLEAR_TASK
                                    or Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS
                        )
                )
            } catch (ex: ActivityNotFoundException) {
                Log.e("PlatLogoActivity", "Couldn't catch a break.")
            }
            true
        })

        return binding.root
    }

    override fun onDestroyView() {
        _binding = null

        super.onDestroyView()
    }
}
