package com.example.attendance;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {
    public static Activity AActivity;
    public static String u_id;
    public static String u_pw;
    public static String major;
    public static String job;
    public static String u_name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT) ;

        AActivity = MainActivity.this;

        u_id = getIntent().getStringExtra("u_id");
        u_pw = getIntent().getStringExtra("u_pw");
        major = getIntent().getStringExtra("major");
        job = getIntent().getStringExtra("job");
        u_name = getIntent().getStringExtra("u_name");

        ImageButton check = (ImageButton) findViewById(R.id.check);
        ImageButton schedule = (ImageButton) findViewById(R.id.schedule);
        ImageButton setting = (ImageButton) findViewById(R.id.setting);
        ImageButton state = (ImageButton) findViewById(R.id.state);

        check.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent checkIntent = new Intent(MainActivity.this, CheckActivity.class);
                startActivity(checkIntent);
            }
        });

        schedule.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent scheduleIntent = new Intent(MainActivity.this, ScheduleFragment.class);
                startActivity(scheduleIntent);
            }
        });

        state.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent stateIntent = new Intent(MainActivity.this, AttendActivity.class);
                startActivity(stateIntent);
            }
        });

        setting.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent settingIntent = new Intent(MainActivity.this, SettingActivity.class);
                MainActivity.this.startActivity(settingIntent);
            }
        });
    }
}