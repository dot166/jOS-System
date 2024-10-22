package jOS.System;

import android.content.Intent;
import android.content.SharedPreferences;
import android.media.AudioManager;
import android.os.Bundle;
import android.util.Log;

import androidx.preference.Preference;
import androidx.preference.PreferenceCategory;
import androidx.preference.PreferenceManager;
import androidx.preference.PreferenceScreen;

import java.util.Objects;

import jOS.Core.jConfigActivity;

public class ConfigActivity extends jConfigActivity {
    @Override
    public int preferenceFragmentValue() {
        return R.string.sdk_settings_fragment_name;
    }
    public static class jSDKConfigFragment extends LauncherSettingsFragment {
        @Override
        public boolean isLIBConfig() {
            return true;
        }
        @Override
        public int preferenceXML() {
            return R.xml.sdk_preferences;
        }
        @Override
        protected boolean extraPrefs(Preference preference) {
            Log.i("Preference Logging", preference.getKey());
            switch (preference.getKey()) {
                case "pref_themeengine":
                    Log.i("Preference Logging", "pref_themeengine Found!!!!");
                    preference.setOnPreferenceClickListener(p -> {
                        Intent intent = new Intent("jOS.ThemeEngine.CONFIG");
                        startActivity(intent);
                        return true;
                    });
                    return true;
            }
            return true;
        }
    }
}
