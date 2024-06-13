package jOS.System;

import android.content.Context;
import android.content.SharedPreferences;
import android.media.AudioManager;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.preference.Preference;
import androidx.preference.PreferenceManager;

public class audioprefs {
    public static boolean parseAudioPrefs(Preference preference) {
        Log.i("Preference Logging", preference.getKey());
        switch (preference.getKey()) {
            case "pref_safevol":
                Log.i("Preference Logging", "pref_safevol Found!!!!");
                preference.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
                    @Override
                    public boolean onPreferenceChange(@NonNull Preference preference, Object newValue) {
                        boolean value = (boolean) newValue;
                        if (value) {
                            AudioManager.enableSafeMediaVolume();
                        } else {
                            AudioManager.disableSafeMediaVolume();
                        }
                        return true;
                    }
                });
                return AudioManager.isSafeMediaVolumeAllowed();
        }
        return true;
    }
}
