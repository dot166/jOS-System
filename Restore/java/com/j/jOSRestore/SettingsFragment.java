package com.j.jOSRestore;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;

import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.PreferenceScreen;

/**
 * This fragment shows the Restore Screen.
 */
public class SettingsFragment extends PreferenceFragmentCompat {
    public static final String KEY_SETTINGS = "pref_restore";
    public static final String KEY_GMS = "pref_grestore";

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.launcher_preferences, rootKey);

        updatePreferences();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    private void updatePreferences() {
        PreferenceScreen screen = getPreferenceScreen();
        for (int i = screen.getPreferenceCount() - 1; i >= 0; i--) {
            Preference preference = screen.getPreference(i);
            if (initPreference(preference)) {
                Log.i(getTag(), String.valueOf(preference));
            } else {
                screen.removePreference(preference);
            }
        }
    }

    /**
     * Initializes a preference. This is called for every preference. Returning false here
     * will remove that preference from the list.
     */
    protected boolean initPreference(Preference preference) {
        PackageManager pm = requireActivity().getPackageManager();
        switch (preference.getKey()) {
            case KEY_SETTINGS:
                preference.setOnPreferenceClickListener(p -> {
                    Intent intent = new Intent("com.stevesoltys.seedvault.RESTORE_BACKUP");
                    startActivity(intent);
                    return isPackageInstalled("org.stevesoltys.seedvault", pm);
                });
                return isPackageInstalled("org.stevesoltys.seedvault", pm);
            case KEY_GMS:
                preference.setOnPreferenceClickListener(p -> {
                    Intent intent = new Intent();
                    intent.setAction("com.google.android.apps.pixelmigrate.ACTION_D2D_MIGRATE_FLOW");
                    startActivity(intent);
                    return isPackageInstalled("com.google.android.apps.restore", pm);
                });
                return isPackageInstalled("com.google.android.apps.restore", pm);
        }
        return true;
    }

    private boolean isPackageInstalled(String packageName, PackageManager packageManager) {
        try {
            packageManager.getPackageInfo(packageName, 0);
            return true;
        } catch (PackageManager.NameNotFoundException e) {
            return false;
        }
    }
}
