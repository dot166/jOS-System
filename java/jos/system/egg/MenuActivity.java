package jos.system.egg;

import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.cardview.widget.CardView;

import io.github.dot166.jlib.app.jActivity;

import jos.system.egg.android_i.*;
import jos.system.egg.android_j.*;
import jos.system.egg.android_l.*;
import jos.system.egg.android_n.neko.*;
import jos.system.R;

public class MenuActivity extends jActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        CardView ics = findViewById(R.id.ics);
        CardView j = findViewById(R.id.j);
        CardView k = findViewById(R.id.k);
        CardView l = findViewById(R.id.l);
        CardView n = findViewById(R.id.n);
        CardView p = findViewById(R.id.p);
        CardView q = findViewById(R.id.q);

        ics.setOnClickListener(v -> startActivity(
                new Intent(MenuActivity.this, Nyandroid.class)
                        .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK
                                | Intent.FLAG_ACTIVITY_CLEAR_TASK
                                | Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS)
        ));

        j.setOnClickListener(v -> startActivity(
                new Intent(MenuActivity.this, BeanBag.class)
                        .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK
                                | Intent.FLAG_ACTIVITY_CLEAR_TASK
                                | Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS)
        ));

        k.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.setComponent(new ComponentName("com.android.systemui", "com.android.systemui.DessertCase"));
            startActivity(intent);
        });

        l.setOnClickListener(v -> startActivity(
                new Intent(MenuActivity.this, LLandActivity.class)
                        .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK
                                | Intent.FLAG_ACTIVITY_CLEAR_TASK
                                | Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS)
        ));

        n.setOnClickListener(v -> {
            final PackageManager pm = getPackageManager();
            final ComponentName cn = new ComponentName(v.getContext(), NekoTile.class);
            if (pm.getComponentEnabledSetting(cn) == PackageManager.COMPONENT_ENABLED_STATE_ENABLED) {
                if (NekoLand.DEBUG) {
                    Log.v("Neko", "Disabling tile.");
                }
                pm.setComponentEnabledSetting(cn, PackageManager.COMPONENT_ENABLED_STATE_DISABLED,
                        PackageManager.DONT_KILL_APP);
                n_toastUp("\uD83D\uDEAB");
            } else {
                if (NekoLand.DEBUG) {
                    Log.v("Neko", "Enabling tile.");
                }
                pm.setComponentEnabledSetting(cn, PackageManager.COMPONENT_ENABLED_STATE_ENABLED,
                        PackageManager.DONT_KILL_APP);
                n_toastUp("\uD83D\uDC31");
            }
        });

        p.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.setComponent(new ComponentName("com.android.egg", "com.android.egg.paint.PaintActivity"));
            startActivity(intent);
        });

        q.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.setComponent(new ComponentName("com.android.egg", "com.android.egg.quares.QuaresActivity"));
            startActivity(intent);
        });
    }

    private void n_toastUp(String s) {
        Toast toast = Toast.makeText(this, s, Toast.LENGTH_SHORT);
//        toast.getView().setBackgroundDrawable(null);// fix NPE
        toast.show();
    }
}