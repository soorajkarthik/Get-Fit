package com.example.sooraj.fitnessapp;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.design.widget.TabItem;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;


import com.example.sooraj.fitnessapp.Model.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    private android.support.v7.widget.Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private PageAdapter pageAdapter;
    private TabItem progressTab;
    private TabItem stepsTab;
    private TabItem foodTab;
    private TabItem profileTab;
    private TextView steps;
    private SensorManager sensorManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(getResources().getString((R.string.app_name)));
        setSupportActionBar(toolbar);

        tabLayout = findViewById(R.id.tabLayout);
        progressTab = findViewById(R.id.progressTab);
        stepsTab = findViewById(R.id.stepsTab);
        foodTab = findViewById(R.id.foodTab);
        profileTab = findViewById(R.id.profileTab);
        viewPager = findViewById(R.id.viewPager);

        steps = findViewById(R.id.stepText);

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

        pageAdapter = new PageAdapter(getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(pageAdapter);

        Sensor countSensor = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);
        sensorManager.registerListener(this, countSensor, SensorManager.SENSOR_DELAY_UI);

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
                 switch (tab.getPosition()) {
                     case 1:
                         toolbar.setBackgroundColor(ContextCompat.getColor(MainActivity.this, R.color.colorAccent));
                         tabLayout.setBackgroundColor(ContextCompat.getColor(MainActivity.this, R.color.colorAccent));
                         getWindow().setStatusBarColor(ContextCompat.getColor(MainActivity.this, R.color.colorAccent));
                         break;

                     case 2:
                         toolbar.setBackgroundColor(ContextCompat.getColor(MainActivity.this, android.R.color.darker_gray));
                         tabLayout.setBackgroundColor(ContextCompat.getColor(MainActivity.this, android.R.color.darker_gray));
                         getWindow().setStatusBarColor(ContextCompat.getColor(MainActivity.this, android.R.color.darker_gray));
                         break;

                     case 3:
                         toolbar.setBackgroundColor(ContextCompat.getColor(MainActivity.this, R.color.colorPrimary));
                         tabLayout.setBackgroundColor(ContextCompat.getColor(MainActivity.this, R.color.colorPrimary));
                         getWindow().setStatusBarColor(ContextCompat.getColor(MainActivity.this, R.color.colorPrimary));
                         break;

                     default:
                         toolbar.setBackgroundColor(ContextCompat.getColor(MainActivity.this, R.color.colorPrimaryDark));
                         tabLayout.setBackgroundColor(ContextCompat.getColor(MainActivity.this, R.color.colorPrimaryDark));
                         getWindow().setStatusBarColor(ContextCompat.getColor(MainActivity.this, R.color.colorPrimaryDark));
                         break;
                 }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
    }



    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        steps.setText(String.valueOf(sensorEvent.values[0]));
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}