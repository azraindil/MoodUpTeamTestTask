package com.example.moodupteamtesttask;

import android.content.Intent;
import android.os.Bundle;

import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.View;

import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {
   @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
       Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment);
       fragment.onActivityResult(requestCode, resultCode, data);
       super.onActivityResult(requestCode, resultCode, data);
    }
}