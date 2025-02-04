package jOS.System;

import android.annotation.SuppressLint;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.AudioManager;
import android.provider.Settings;
import android.util.Log;

public class jOSSystemBroadcastReceiver extends BroadcastReceiver {
    private static final String TAG = jOSSystemBroadcastReceiver.class.getSimpleName();

    @Override
    public void onReceive(final Context context, @SuppressLint("UnsafeIntentLaunch") final Intent intent) {
        final String action = intent.getAction();
        if (Intent.ACTION_BOOT_COMPLETED.equals(action)) {
            Log.i(TAG, "Boot has been completed");
            IntentFilter intentFilter = new IntentFilter(NotificationManager.ACTION_INTERRUPTION_FILTER_CHANGED);

            jOSSystemBroadcastReceiver mReceiver = new jOSSystemBroadcastReceiver();
            context.registerReceiver(mReceiver, intentFilter);
        } else if (NotificationManager.ACTION_INTERRUPTION_FILTER_CHANGED.equals(action)) {
            Log.i(TAG, "DnD Changed (i think???)");
            AudioManager audioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
            final NotificationManager manager = context.getSystemService(NotificationManager.class);
            final int zenMode = manager.getZenMode();

            switch (zenMode) {
                case Settings.Global.ZEN_MODE_ALARMS:
                case Settings.Global.ZEN_MODE_IMPORTANT_INTERRUPTIONS:
                case Settings.Global.ZEN_MODE_NO_INTERRUPTIONS:
                    audioManager.setRingerModeInternal(AudioManager.RINGER_MODE_VIBRATE);
                    break;
                case Settings.Global.ZEN_MODE_OFF:
                default:
                    audioManager.setRingerModeInternal(AudioManager.RINGER_MODE_NORMAL);
                    break;
            }
        }
    }
}
