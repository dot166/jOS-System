package jOS.System;

import android.annotation.SuppressLint;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.ext.settings.ExtSettings;
import android.media.AudioManager;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;

public class jOSSystemBroadcastReceiver extends BroadcastReceiver {
    private static final String TAG = jOSSystemBroadcastReceiver.class.getSimpleName();
    private int mLastZenMode;

    @Override
    public void onReceive(final Context context, @SuppressLint("UnsafeIntentLaunch") final Intent intent) {
        final String action = intent.getAction();
        if (Intent.ACTION_BOOT_COMPLETED.equals(action)) {
            Log.i(TAG, "Boot has been completed");

            Handler handler = new Handler(Looper.getMainLooper());
            Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    // force enable it
                    //if (ExtSettings.ENABLE_VIBRATE_ON_DO_NOT_DISTURB.get(context) == true) {
                        AudioManager audioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
                        final NotificationManager manager = context.getSystemService(NotificationManager.class);
                        final int zenMode = manager.getCurrentInterruptionFilter()                                      ;

                        if (mLastZenMode != zenMode) {
                            Log.i(TAG, "DnD Changed (i think???)");
                            switch (zenMode) {
                                case NotificationManager.INTERRUPTION_FILTER_ALARMS:
                                case NotificationManager.INTERRUPTION_FILTER_PRIORITY:
                                case NotificationManager.INTERRUPTION_FILTER_NONE:
                                case NotificationManager.INTERRUPTION_FILTER_UNKNOWN:
                                    audioManager.setRingerModeInternal(AudioManager.RINGER_MODE_VIBRATE);
                                    break;
                                case NotificationManager.INTERRUPTION_FILTER_ALL:
                                    audioManager.setRingerModeInternal(AudioManager.RINGER_MODE_NORMAL);
                                    break;
                            }
                            mLastZenMode = zenMode;
                        }
                    //} else {
                    //    Log.i(TAG, "Vibrate on DnD is Disabled by the user");
                    //}
                    handler.postDelayed(this, 100); // Poll every 100 milliseconds
                }
            };
            handler.post(runnable);
        }
    }
}
