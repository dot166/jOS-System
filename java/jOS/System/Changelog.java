package jOS.System;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import io.github.dot166.jLib.jOS.Build;
import io.github.dot166.jLib.app.jWebActivity;

public class Changelog extends jWebActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        configure("https://dot166.github.io/jOS-Updates/" + Build.jOS_RELEASE.replaceAll("-\\(.*","") + "-changelog.html");
        super.onCreate(savedInstanceState);
    }
}
