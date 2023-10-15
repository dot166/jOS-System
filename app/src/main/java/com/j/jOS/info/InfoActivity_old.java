package com.j.jOS.info;

import static android.content.ContentValues.TAG;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import lineageos.os.*;

import com.j.jOS.R;

public class InfoActivity_old extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        final String LINEAGEOS = Build.LINEAGEOS_VERSION;
        Log.i(TAG, LINEAGEOS);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        TextView tv1 = (TextView)findViewById(R.id.textView9);
        tv1.setText(LINEAGEOS);
    }
}
