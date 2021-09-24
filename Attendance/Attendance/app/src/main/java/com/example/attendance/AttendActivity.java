package com.example.attendance;

import android.app.AlertDialog;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

public class AttendActivity extends AppCompatActivity {

    private ListView AttendListView;
    private AttendListAdapter adapter;
    private List<Attend> attendList;

    private ArrayAdapter yearAdapter;
    private ArrayAdapter monthAdapter;
    private ArrayAdapter dayAdapter;

    private Spinner yearSpinner;
    private Spinner monthSpinner;
    private Spinner daySpinner;

    public static String date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attend);

        AttendListView = (ListView) findViewById(R.id.attendListView);
        attendList = new ArrayList<Attend>();
        adapter = new AttendListAdapter(getApplicationContext(), attendList);
        AttendListView.setAdapter(adapter);

        yearSpinner = (Spinner) findViewById(R.id.yearSpinner);
        yearAdapter = ArrayAdapter.createFromResource(this, R.array.date_year, android.R.layout.simple_spinner_dropdown_item);
        yearSpinner.setAdapter(yearAdapter);

        monthSpinner = (Spinner) findViewById(R.id.monthSpinner);
        monthAdapter = ArrayAdapter.createFromResource(this, R.array.date_month, android.R.layout.simple_spinner_dropdown_item);
        monthSpinner.setAdapter(monthAdapter);

        daySpinner = (Spinner) findViewById(R.id.daySpinner);
        dayAdapter = ArrayAdapter.createFromResource(this, R.array.date_day, android.R.layout.simple_spinner_dropdown_item);
        daySpinner.setAdapter(dayAdapter);



        Button bring = (Button) findViewById(R.id.bring);
        bring.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                date =  yearSpinner.getSelectedItem().toString() + monthSpinner.getSelectedItem().toString() + daySpinner.getSelectedItem().toString();
                //Toast.makeText(AttendActivity.this, date, Toast.LENGTH_LONG).show();
                new AttendActivity.BackgroundTask().execute();
            }
        });

    }


    class BackgroundTask extends AsyncTask<Void, Void, String>
    {
        String target;
        @Override
        protected void onPreExecute() {
            try {
                target = "http://172.30.1.4/AttendList.php?u_id=" + URLEncoder.encode(MainActivity.u_id,"UTF-8") + "&date=" + URLEncoder.encode(AttendActivity.date,"UTF-8");
            }
            catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }

        @Override
        protected String doInBackground(Void... voids) {
            try{
                URL url = new URL(target);
                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                String temp;
                StringBuilder stringBuilder = new StringBuilder();

                while((temp = bufferedReader.readLine()) != null){
                    stringBuilder.append(temp + "\n");
                }

                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return stringBuilder.toString().trim();

            }
            catch(Exception e){
                e.printStackTrace();
            }
            return null;
        }

        @Override
        public void onProgressUpdate(Void... values) {
            super.onProgressUpdate();
        }
        @Override
        public void onPostExecute(String result) {
            try {
                JSONObject jsonObject = new JSONObject(result);
                JSONArray jsonArray = jsonObject.getJSONArray("response");
                int count = 0;
                String courseProfessor;
                String courseTime;
                String courseTitle;
                String courseRoom;
                String three;
                String Check_time;

                attendList.clear();
                while (count < jsonArray.length())
                {
                    JSONObject object =jsonArray.getJSONObject(count);
                    courseProfessor = object.getString("courseProfessor");
                    courseTime = object.getString("courseTime");
                    courseTitle = object.getString("courseTitle");
                    courseRoom = object.getString("courseRoom");
                    three = object.getString("three");
                    Check_time = object.getString("Check_time");
                    Attend attend = new Attend(courseTitle, courseProfessor, courseTime, courseRoom, three, Check_time);
                    attendList.add(attend);
                    adapter.notifyDataSetChanged();
                    count++;
                }
                if (count == 0) {
                    AlertDialog dialog;
                    AlertDialog.Builder builder = new AlertDialog.Builder(AttendActivity.this);
                    dialog = builder.setMessage("강의가 없습니다")
                            .setPositiveButton("확인", null)
                            .create();
                    dialog.show();
                }

            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}