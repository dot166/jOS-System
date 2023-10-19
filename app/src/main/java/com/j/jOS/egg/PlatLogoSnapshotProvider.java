package com.j.jOS.egg;

import static com.j.jOS.egg.PlatLogoActivity.BGCOLOR;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;


public class PlatLogoSnapshotProvider extends com.dede.basic.PlatLogoSnapshotProvider {

    @Override
    public boolean getIncludeBackground() {
        return true;
    }

    @Override
    public View create(Context context) {
        FrameLayout mContent = new FrameLayout(context);

        final FrameLayout.LayoutParams lp = new FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.WRAP_CONTENT,
                FrameLayout.LayoutParams.WRAP_CONTENT);
        lp.gravity = Gravity.CENTER;

        final ImageView logo = new ImageView(context);
        logo.setImageResource(R.drawable.k_platlogo);
        logo.setScaleType(ImageView.ScaleType.CENTER_INSIDE);

        mContent.setBackgroundColor(BGCOLOR);
        mContent.addView(logo, lp);
        return mContent;
    }
}
