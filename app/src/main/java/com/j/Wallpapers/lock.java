package com.j.Wallpapers;

import android.app.Activity;
import android.app.WallpaperManager;
import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.j.jOS.R;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.j.jOS.databinding.FragmentListwallpapersBinding;
import com.j.jOS.databinding.FragmentLockBinding;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class lock extends Activity implements WallpaperSelectListener {
    private RecyclerView wallpaperRecyclerView;
    private WallpaperGalleryRecyclerAdapter wallpaperGalleryRecyclerAdapter;
    private List<Wallpaper> wallpapers;
    private FragmentLockBinding binding;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        wallpapers = new ArrayList<>();
        wallpapers.add(new Wallpaper("Default", "https://raw.githubusercontent.com/jf916/jOS-Wallpaper-Files/main/Lock_screen/0.jpeg"));
        wallpapers.add(new Wallpaper("[bi panic]", "https://raw.githubusercontent.com/jf916/jOS-Wallpaper-Files/main/Lock_screen/1.jpeg"));

        wallpaperGalleryRecyclerAdapter = new WallpaperGalleryRecyclerAdapter(this);
        wallpaperGalleryRecyclerAdapter.setWallpapers(wallpapers);
        binding = FragmentLockBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        wallpaperRecyclerView = binding.fragmentListwallpapersRecyclerView;
        wallpaperRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        wallpaperRecyclerView.setAdapter(wallpaperGalleryRecyclerAdapter);

        binding.skipLock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_MAIN);
                intent.setComponent(new ComponentName("com.android.wallpaper", "com.android.wallpaper.picker.CategoryPickerActivity"));
                try {
                    startActivity(intent);
                } catch (ActivityNotFoundException e) {
                    // Define what your app should do if no activity can handle the intent.
                    Toast.makeText(getApplicationContext(), "ERROR", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    private void setLockScreenWallpaper(Bitmap bitmap) {
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                WallpaperManager.getInstance(getApplicationContext()).setBitmap(bitmap, null, true, WallpaperManager.FLAG_LOCK);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onWallpaperSelect(Wallpaper wallpaper) {

        Glide.with(getApplicationContext())
                .asBitmap()
                .load(wallpaper.getImageUri())
                .into(new SimpleTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(Bitmap resource, Transition<? super Bitmap> transition) {
                        setLockScreenWallpaper(resource);
                        Toast.makeText(getApplicationContext(), wallpaper.getTitle() + " has been selected as the lock screen wallpaper", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(Intent.ACTION_MAIN);
                        intent.setComponent(new ComponentName("com.android.wallpaper", "com.android.wallpaper.picker.CategoryPickerActivity"));
                        try {
                            startActivity(intent);
                        } catch (ActivityNotFoundException e) {
                            // Define what your app should do if no activity can handle the intent.
                            Toast.makeText(getApplicationContext(), "ERROR", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}
