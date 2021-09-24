package com.example.attendance;

import android.app.AlertDialog;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

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

public class proManageActivity extends AppCompatActivity {

    private ListView proManageListView;
    private proManageListAdapter adapter;
    private List<proManage> proManageList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pro_manage);

        proManageListView = (ListView) findViewById(R.id.proManageListView);
        proManageList = new ArrayList<proManage>();
        adapter = new proManageListAdapter(getApplicationContext(), proManageList);
        proManageListView.setAdapter(adapter);
        new proManageActivity.BackgroundTask().execute();
    }

    class BackgroundTask extends AsyncTask<Void, Void, String>
    {
        String target;
        @Override
        protected void onPreExecute() {
            try {
                target = "http://172.30.1.4/proAttendList.php?proname=" + URLEncoder.encode(proMainActivity.proname,"UTF-8");
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
                String courseTitle;
                String u_name;
                String courseTime;
                String courseRoom;
                String three;
                String Check_time;

                proManageList.clear();
                while (count < jsonArray.length())
                {
                    JSONObject object =jsonArray.getJSONObject(count);
                    courseTitle = object.getString("courseTitle");
                    u_name = object.getString("u_name");
                    courseTime = object.getString("courseTime");
                    courseRoom = object.getString("courseRoom");
                    three = object.getString("three");
                    Check_time = object.getString("Check_time");
                    proManage promanage = new proManage(courseTitle, u_name, courseTime, courseRoom, three, Check_time);
                    proManageList.add(promanage);
                    adapter.notifyDataSetChanged();
                    count++;
                }
                if (count == 0) {
                    AlertDialog dialog;
                    AlertDialog.Builder builder = new AlertDialog.Builder(proManageActivity.this);
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
