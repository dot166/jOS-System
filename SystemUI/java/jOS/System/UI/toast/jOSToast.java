package jOS.System.UI.toast;

import com.android.systemui.toast.jOSToastLink;
import android.annotation.NonNull;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import jOS.System.UI.R;

public class jOSToast implements jOSToastLink {

    final Context mContext;
    private final LayoutInflater mLayoutInflater;

    @Override
    public ToastPlugin.Toast getToast(LayoutInflater layoutInflater, Context context) {
        return new jOSToast(layoutInflator, context);
    }

    jOSToast(LayoutInflater layoutInflater, Context context) {
        mLayoutInflater = layoutInflater;
        mContext = context;
    }

    @Override
    @NonNull
    public View getView() {
        final View toastView = mLayoutInflater.inflate(
                    R.layout.j_text_toast, null);
        final TextView textView = toastView.findViewById(R.id.text);
        final ImageView iconView = toastView.findViewById(R.id.icon);
        textView.setText(mText);

        ApplicationInfo appInfo = null;
        try {
            appInfo = mContext.getPackageManager()
                    .getApplicationInfoAsUser(mPackageName, 0, mUserId);
        } catch (PackageManager.NameNotFoundException e) {
            Log.e(TAG, "Package name not found package=" + mPackageName
                    + " user=" + mUserId);
        }

        if (appInfo != null && appInfo.targetSdkVersion < Build.VERSION_CODES.S) {
            // no two-line limit
            textView.setMaxLines(Integer.MAX_VALUE);

            // no app icon
            toastView.findViewById(R.id.icon).setVisibility(View.GONE);
        } else {
            Drawable icon = getBadgedIcon(mContext, mPackageName, mUserId);
            if (icon == null) {
                iconView.setVisibility(View.GONE);
            } else {
                iconView.setImageDrawable(icon);
                if (appInfo == null) {
                    Log.d(TAG, "No appInfo for pkg=" + mPackageName + " usr=" + mUserId);
                } else if (appInfo.labelRes != 0) {
                    try {
                        Resources res = mContext.getPackageManager().getResourcesForApplication(
                                appInfo,
                                new Configuration(mContext.getResources().getConfiguration()));
                        iconView.setContentDescription(res.getString(appInfo.labelRes));
                    } catch (PackageManager.NameNotFoundException e) {
                        Log.d(TAG, "Cannot find application resources for icon label.");
                    }
                }
            }
        }
        return toastView;
    }
}