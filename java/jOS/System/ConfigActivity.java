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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        AudioManager am = new AudioManager(this);
        boolean oldValue = am.getSafeMediaVolumeState();
        SharedPreferences.Editor prefs = PreferenceManager.getDefaultSharedPreferences(this).edit();
        prefs.putBoolean("pref_safevol", oldValue);
        prefs.commit();
    }
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
        public void initPreference(String rootKey) {
            super.initPreference(rootKey);
            PreferenceScreen screen = this.getPreferenceScreen();
            addPreferencesFromResource(R.xml.audio_preference);

            for(int i = screen.getPreferenceCount() - 1; i >= 0; --i) {
                Preference preference = screen.getPreference(i);
                if (Objects.equals(preference.getKey(), "audio_category")) {
                    PreferenceCategory category = (PreferenceCategory)preference;

                    for(int i2 = category.getPreferenceCount() - 1; i2 >= 0; --i2) {
                        Preference preference2 = category.getPreference(i2);
                        if (!this.configPreference(preference2)) {
                            category.removePreference(preference2);
                        }
                    }
                }
            }
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
            return audioprefs.parseAudioPrefs(preference);
        }
    }
}
