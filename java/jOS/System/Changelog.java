package jOS.System;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import jOS.Core.Build;

public class Changelog extends jWebActivity{

    @Override
    public void onCreate(Bundle savedInstanceState) {
        configure("https://dot166.github.io/jOS-Updates/" + Build.jOS_RELEASE.replaceAll("-\\(.*","") + "-changelog.html", true, true, false, true);
        super.onCreate(savedInstanceState);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeActionContentDescription(androidx.appcompat.R.string.abc_action_bar_up_description);
        } else {
            Log.e("ActionBar2", "no actionbar found");
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                getOnBackPressedDispatcher().onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
