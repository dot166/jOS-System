package jOS.System;

import jOS.Core.jConfigActivity;

public class ConfigActivity extends jConfigActivity {
    @Override
    public int appName() {
        return R.string.sdk_settings_name;
    }
    @Override
    public int preferenceFragmentValue() {
        return R.string.sdk_settings_fragment_name;
    }
    public static class jSDKConfigFragment extends jConfigActivity.LauncherSettingsFragment {
        @Override
        public boolean isSDKConfig() {
            return true;
        }
        @Override
        public int preferenceXML() {
            return R.xml.sdk_preferences;
        }
    }
}
