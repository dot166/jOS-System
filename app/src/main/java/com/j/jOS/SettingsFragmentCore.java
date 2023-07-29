package com.j.jOS;

import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class SettingsFragmentCore extends Fragment {
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_settings_core, container, false);

        view.findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_MAIN);
                intent.setComponent(new ComponentName("com.android.settings", "com.android.settings.Settings"));
                startActivity(intent);
            }
        });

        view.findViewById(R.id.button2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_MAIN);
                intent.setComponent(new ComponentName("com.google.android.gms", "com.google.android.gms.app.settings.GoogleSettingsLink"));
                try {
                    startActivity(intent);
                } catch (ActivityNotFoundException e) {
                    // Define what your app should do if no activity can handle the intent.
                    intent.setComponent(new ComponentName("com.google.android.gms", "com.google.android.gms.app.settings.GoogleSettingsActivity"));
                    try {
                        startActivity(intent);
                    } catch (ActivityNotFoundException f) {
                        // Define what your app should do if no activity can handle the intent.
                        Toast.makeText(getContext(), "Google Play Services is not installed", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        view.findViewById(R.id.button3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_MAIN);
                intent.setComponent(new ComponentName("com.j.jOSLauncher", "com.j.jOSLauncher.ui.preferences.PreferenceActivity"));
                try {
                    startActivity(intent);
                } catch (ActivityNotFoundException e) {
                    // Define what your app should do if no activity can handle the intent.
                    intent.setComponent(new ComponentName("com.j.jOSLauncher.debug", "com.j.jOSLauncher.ui.preferences.PreferenceActivity"));
                    startActivity(intent);
                }
            }
        });

        view.findViewById(R.id.button4).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_MAIN);
                intent.setComponent(new ComponentName("com.j.inputmethod.latin", "com.j.inputmethod.latin.setup.SetupWizardActivity"));
                startActivity(intent);
            }
        });


        return view;
    }
}