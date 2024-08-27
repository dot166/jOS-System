package jOS.System;

import android.media.AudioManager;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.preference.Preference;

public class audioprefs {
    public static boolean parseAudioPrefs(Preference preference) {
        AudioManager am = new AudioManager(preference.getContext());
        Log.i("Preference Logging", preference.getKey());
        switch (preference.getKey()) {
            case "pref_safevol":
                Log.i("Preference Logging", "pref_safevol Found!!!!");
                preference.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
                    @Override
                    public boolean onPreferenceChange(@NonNull Preference preference, Object newValue) {
                        boolean value = (boolean) newValue;
                        if (value) {
                            am.enableSafeMediaVolume();
                        } else {
                            am.disableSafeMediaVolume();
                        }
                        return true;
                    }
                });
                return am.isSafeMediaVolumeAllowed();
        }
        return true;
    }
}
