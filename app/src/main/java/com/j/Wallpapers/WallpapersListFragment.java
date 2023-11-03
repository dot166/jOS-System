package com.j.Wallpapers;

import android.app.Activity;
import android.app.WallpaperManager;
import android.content.ComponentName;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.j.jOS.BuildConfig;
import com.j.jOS.databinding.FragmentListwallpapersBinding;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class WallpapersListFragment extends Activity implements WallpaperSelectListener {
    private RecyclerView wallpaperRecyclerView;
    private WallpaperGalleryRecyclerAdapter wallpaperGalleryRecyclerAdapter;
    private List<Wallpaper> wallpapers;
    private FragmentListwallpapersBinding binding;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        wallpapers = new ArrayList<>();
        wallpapers.add(new Wallpaper("[bi panic]", "https://raw.githubusercontent.com/jf916/jOS-Wallpaper-Files/main/Home_screen/0.jpeg"));
        wallpapers.add(new Wallpaper("kimetsu no yaiba", "https://raw.githubusercontent.com/jf916/jOS-Wallpaper-Files/main/Home_screen/1.jpeg"));
        wallpapers.add(new Wallpaper("Field", "https://images.pexels.com/photos/35857/amazing-beautiful-breathtaking-clouds.jpg?auto=compress&cs=tinysrgb&dpr=2&h=750&w=1260"));
        wallpapers.add(new Wallpaper("Clouds", "https://images.pexels.com/photos/2088205/pexels-photo-2088205.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=750&w=1260"));
        wallpapers.add(new Wallpaper("Condensation", "https://images.pexels.com/photos/891030/pexels-photo-891030.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=750&w=1260"));

        wallpaperGalleryRecyclerAdapter = new WallpaperGalleryRecyclerAdapter(this);
        wallpaperGalleryRecyclerAdapter.setWallpapers(wallpapers);
        binding = FragmentListwallpapersBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        wallpaperRecyclerView = binding.fragmentListwallpapersRecyclerView;
        wallpaperRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        wallpaperRecyclerView.setAdapter(wallpaperGalleryRecyclerAdapter);

        binding.skipHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                next();
            }
        });
    }

    private void setHomeScreenWallpaper(Bitmap bitmap) {
        try {
            WallpaperManager.getInstance(getApplicationContext()).setBitmap(bitmap);
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
                        setHomeScreenWallpaper(resource);
                        Toast.makeText(getApplicationContext(), wallpaper.getTitle() + " has been selected as the home screen wallpaper", Toast.LENGTH_SHORT).show();
                        next();
                    }
                });
    }
    public void next() {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.setComponent(new ComponentName(BuildConfig.APPLICATION_ID, "com.j.Wallpapers.lock"));
        startActivity(intent);
    }
}


