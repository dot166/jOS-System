package jOS.System.lib;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AnticipateOvershootInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

public class sdkplaceholder extends Activity {
    FrameLayout mContent;
    static final int BGCOLOR = 0xffed1d24;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final TextView tv = new TextView(this);
        if (light != null) tv.setTypeface(light);
        tv.setTextSize(30);
        tv.setPadding(p, p, p, p);
        tv.setTextColor(0xFFFFFFFF);
        tv.setGravity(Gravity.CENTER);
        tv.setTransformationMethod(TransformationMethodUtils.createAllCapsTransformationMethod(this));
        tv.setText("SDK TEST");
        tv.setAlpha(0f);
        tv.setVisibility(View.VISIBLE);
        tv.animate().alpha(1f).setDuration(1000).setStartDelay(1000).start();
        final View bg = new View(this);
        bg.setBackgroundColor(BGCOLOR);
        bg.setAlpha(0f);
        mContent = new FrameLayout(this);
        mContent.addView(bg);
        bg.setScaleX(0.01f);
        bg.animate().alpha(1f).scaleX(1f).setStartDelay(500).start();
        final FrameLayout.LayoutParams lp2 = new FrameLayout.LayoutParams(lp);
        lp2.gravity = Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL;
        lp2.bottomMargin = 10 * p;

        mContent.addView(tv, lp2);
    }
}
