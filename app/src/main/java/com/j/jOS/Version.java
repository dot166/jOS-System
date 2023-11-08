package com.j.jOS;

import android.os.SystemProperties;

public class Version {
    private static final String KEY_JOS_VERSION_PROP = "ro.j.osversion";
    public static CharSequence os_ver = SystemProperties.get(KEY_JOS_VERSION_PROP,
            mContext.getString(R.string.unknown));
}
