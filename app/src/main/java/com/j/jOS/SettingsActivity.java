package com.j.jOS;

import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.VisibleForTesting;
import androidx.core.view.WindowCompat;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.PreferenceFragmentCompat.OnPreferenceStartFragmentCallback;
import androidx.preference.PreferenceFragmentCompat.OnPreferenceStartScreenCallback;
import androidx.preference.PreferenceScreen;

/**
 * Settings activity for Launcher. Currently implements the following setting: Allow rotation
 */
public class SettingsActivity extends FragmentActivity
        implements OnPreferenceStartFragmentCallback, OnPreferenceStartScreenCallback,
        SharedPreferences.OnSharedPreferenceChangeListener {

    public static final String EXTRA_FRAGMENT_ARG_KEY = ":settings:fragment_args_key";
    public static final String SAVE_HIGHLIGHTED_KEY = "android:preference_highlighted";

    @VisibleForTesting
    static final String EXTRA_FRAGMENT = ":settings:fragment";
    @VisibleForTesting
    static final String EXTRA_FRAGMENT_ARGS = ":settings:fragment_args";
    public static final String KEY_SETTINGS = "pref_system";
    public static final String KEY_GMS = "pref_gms";
    public static final String KEY_LAUNCHER3 = "pref_launcher3";
    public static final String KEY_KBD = "pref_latinime";
    public static final String KEY_WALL = "pref_wall";
    public static final String KEY_INFO = "pref_info";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_activity);
        //setActionBar(findViewById(R.id.action_bar));
        WindowCompat.setDecorFitsSystemWindows(getWindow(), false);

        Intent intent = getIntent();
        if (intent.hasExtra(EXTRA_FRAGMENT) || intent.hasExtra(EXTRA_FRAGMENT_ARGS)
                || intent.hasExtra(EXTRA_FRAGMENT_ARG_KEY)) {
            getActionBar().setDisplayHomeAsUpEnabled(true);
        }

        if (savedInstanceState == null) {
            Bundle args = intent.getBundleExtra(EXTRA_FRAGMENT_ARGS);
            if (args == null) {
                args = new Bundle();
            }

            String prefKey = intent.getStringExtra(EXTRA_FRAGMENT_ARG_KEY);
            if (!TextUtils.isEmpty(prefKey)) {
                args.putString(EXTRA_FRAGMENT_ARG_KEY, prefKey);
            }

            final FragmentManager fm = getSupportFragmentManager();
            final Fragment f = fm.getFragmentFactory().instantiate(getClassLoader(),
                    getPreferenceFragment());
            f.setArguments(args);
            // Display the fragment as the main content.
            fm.beginTransaction().replace(R.id.content_frame, f).commit();
        }
    }

    /**
     * Obtains the preference fragment to instantiate in this activity.
     *
     * @return the preference fragment class
     * @throws IllegalArgumentException if the fragment is unknown to this activity
     */
    private String getPreferenceFragment() {
        String preferenceFragment = getIntent().getStringExtra(EXTRA_FRAGMENT);
        String defaultFragment = getString(R.string.settings_fragment_name);

        if (TextUtils.isEmpty(preferenceFragment)) {
            return defaultFragment;
        } else if (!preferenceFragment.equals(defaultFragment)) {
            throw new IllegalArgumentException(
                    "Invalid fragment for this activity: " + preferenceFragment);
        } else {
            return preferenceFragment;
        }
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
    }

    private boolean startPreference(String fragment, Bundle args, String key) {
        if (getSupportFragmentManager().isStateSaved()) {
            // Sometimes onClick can come after onPause because of being posted on the handler.
            // Skip starting new preferences in that case.
            return false;
        }
        final FragmentManager fm = getSupportFragmentManager();
        final Fragment f = fm.getFragmentFactory().instantiate(getClassLoader(), fragment);
        if (f instanceof DialogFragment) {
            f.setArguments(args);
            ((DialogFragment) f).show(fm, key);
        } else {
            startActivity(new Intent(this, SettingsActivity.class)
                    .putExtra(EXTRA_FRAGMENT, fragment)
                    .putExtra(EXTRA_FRAGMENT_ARGS, args));
        }
        return true;
    }

    @Override
    public boolean onPreferenceStartFragment(
            PreferenceFragmentCompat preferenceFragment, Preference pref) {
        return startPreference(pref.getFragment(), pref.getExtras(), pref.getKey());
    }

    @Override
    public boolean onPreferenceStartScreen(PreferenceFragmentCompat caller, PreferenceScreen pref) {
        Bundle args = new Bundle();
        args.putString(PreferenceFragmentCompat.ARG_PREFERENCE_ROOT, pref.getKey());
        return startPreference(getString(R.string.settings_fragment_name), args, pref.getKey());
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * This fragment shows the launcher preferences.
     */
    public static class LauncherSettingsFragment extends PreferenceFragmentCompat {

        private String mHighLightKey;
        private boolean mPreferenceHighlighted = false;

        @Override
        public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
            final Bundle args = getArguments();
            mHighLightKey = args == null ? null : args.getString(EXTRA_FRAGMENT_ARG_KEY);
            if (rootKey == null && !TextUtils.isEmpty(mHighLightKey)) {
                rootKey = getParentKeyForPref(mHighLightKey);
            }

            if (savedInstanceState != null) {
                mPreferenceHighlighted = savedInstanceState.getBoolean(SAVE_HIGHLIGHTED_KEY);
            }
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


            @Override
        public void onViewCreated(View view, Bundle savedInstanceState) {
            super.onViewCreated(view, savedInstanceState);
            View listView = getListView();
            final int bottomPadding = listView.getPaddingBottom();
            listView.setOnApplyWindowInsetsListener((v, insets) -> {
                v.setPadding(
                        v.getPaddingLeft(),
                        v.getPaddingTop(),
                        v.getPaddingRight(),
                        bottomPadding + insets.getSystemWindowInsetBottom());
                return insets.consumeSystemWindowInsets();
            });
        }

        @Override
        public void onSaveInstanceState(Bundle outState) {
            super.onSaveInstanceState(outState);
            outState.putBoolean(SAVE_HIGHLIGHTED_KEY, mPreferenceHighlighted);
        }

        protected String getParentKeyForPref(String key) {
            return null;
        }

        /**
         * Initializes a preference. This is called for every preference. Returning false here
         * will remove that preference from the list.
         */
        protected boolean initPreference(Preference preference) {
            switch (preference.getKey()) {
                case KEY_SETTINGS:
                    preference.setOnPreferenceClickListener(p -> {
                        Intent intent = new Intent(Intent.ACTION_MAIN);
                        intent.setComponent(new ComponentName("com.android.settings", "com.android.settings.Settings"));
                        startActivity(intent);
                        return true;
                    });
                    return true;
                case KEY_GMS:
                    preference.setOnPreferenceClickListener(p -> {
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
                        return true;
                    });
                    return true;
                case KEY_LAUNCHER3:
                    preference.setOnPreferenceClickListener(p -> {
                        Intent intent = new Intent(Intent.ACTION_MAIN);
                        intent.setComponent(new ComponentName("com.android.launcher3", "com.android.launcher3.settings.SettingsActivity"));
                        try {
                            startActivity(intent);
                        } catch (ActivityNotFoundException e) {
                            // Define what your app should do if no activity can handle the intent.
                            Toast.makeText(getContext(), "com.android.launcher3 is not installed", Toast.LENGTH_SHORT).show();
                        }
                        return true;
                    });
                    return true;
                case KEY_KBD:
                    preference.setOnPreferenceClickListener(p -> {
                        Intent intent = new Intent(Intent.ACTION_MAIN);
                        intent.setComponent(new ComponentName("com.android.inputmethod.latin", "com.android.inputmethod.latin.setup.SetupWizardActivity"));
                        startActivity(intent);
                        return true;
                    });
                    return true;
                case KEY_WALL:
                    preference.setOnPreferenceClickListener(p -> {
                        Intent intent = new Intent(Intent.ACTION_MAIN);
                        intent.setComponent(new ComponentName(BuildConfig.APPLICATION_ID, "com.j.Wallpapers.WallpaperActivity"));
                        startActivity(intent);
                        return true;
                    });
                    return true;
                case KEY_INFO:
                    preference.setOnPreferenceClickListener(p -> {
                        Intent intent = new Intent(Intent.ACTION_MAIN);
                        intent.setComponent(new ComponentName(BuildConfig.APPLICATION_ID, "com.j.jOS.info.InfoActivity"));
                        startActivity(intent);
                        return true;
                    });
                    return true;
            }
            return true;
        }
    }
}
