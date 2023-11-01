package com.j.Wallpapers;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;

import com.j.jOS.BuildConfig;
import com.j.jOS.R;

public class WallpaperActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.setComponent(new ComponentName(BuildConfig.APPLICATION_ID, "com.j.Wallpapers.WallpapersListFragment"));
        startActivity(intent);
        // Push the button
        setContentView(R.layout.again_again);
    }
}
