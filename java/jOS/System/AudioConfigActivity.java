package jOS.System;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.media.AudioManager;

import androidx.preference.Preference;
import androidx.preference.PreferenceCategory;
import androidx.preference.PreferenceManager;
import androidx.preference.PreferenceScreen;

import java.util.Objects;

import jOS.Core.jConfigActivity;

public class AudioConfigActivity extends jConfigActivity {
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
        return R.string.audio_settings_fragment_name;
    }
    public static class AudioConfigFragment extends LauncherSettingsFragment {
        @Override
        public boolean hideLIB() {
            return true;
        }
        @Override
        public int preferenceXML() {
            return R.xml.audio_preferences;
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
            return audioprefs.parseAudioPrefs(preference);
        }
    }
}
