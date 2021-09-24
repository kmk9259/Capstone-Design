package com.example.attendance;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ScheduleFragment extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_schedule);

        final Button courseButton = (Button) findViewById(R.id.courseButton);
        final Button scheduleButton = (Button) findViewById(R.id.scheduleButton);
        final Button scheduleDelete = (Button) findViewById(R.id.scheduleDelete);

        final LinearLayout blank = (LinearLayout) findViewById(R.id.blank);

        courseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                blank.setVisibility(View.GONE);
                courseButton.setBackgroundColor(getResources().getColor(R.color.colorPrimary2));
                scheduleButton.setBackgroundColor(getResources().getColor(R.color.colorPrimary3));
                scheduleDelete.setBackgroundColor(getResources().getColor(R.color.colorPrimary3));
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragment, new CourseFragment());
                fragmentTransaction.commit();
            }
        });

        scheduleDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                blank.setVisibility(View.GONE);
                courseButton.setBackgroundColor(getResources().getColor(R.color.colorPrimary3));
                scheduleButton.setBackgroundColor(getResources().getColor(R.color.colorPrimary3));
                scheduleDelete.setBackgroundColor(getResources().getColor(R.color.colorPrimary2));
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragment, new DeleteScheduleFragment());
                fragmentTransaction.commit();
            }
        });

        scheduleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                blank.setVisibility(View.GONE);
                courseButton.setBackgroundColor(getResources().getColor(R.color.colorPrimary3));
                scheduleButton.setBackgroundColor(getResources().getColor(R.color.colorPrimary2));
                scheduleDelete.setBackgroundColor(getResources().getColor(R.color.colorPrimary3));
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragment, new StaticsFragment());
                fragmentTransaction.commit();
            }
        });
    }

}
