package com.example.attendance;

import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;


public class StaticsFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public StaticsFragment() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static StaticsFragment newInstance(String param1, String param2) {
        StaticsFragment fragment = new StaticsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    private TextView monday[] = new TextView[12];
    private TextView tuesday[] = new TextView[12];
    private TextView wednesday[] = new TextView[12];
    private TextView thursday[] = new TextView[12];
    private TextView friday[] = new TextView[12];
    private TextView saturday[] = new TextView[12];
    private Schedule schedule = new Schedule();

    @Override
    public void onActivityCreated(Bundle b){
        super.onActivityCreated(b);
        monday[0] = (TextView) getView().findViewById(R.id.monday0);
        monday[1] = (TextView) getView().findViewById(R.id.monday1);
        monday[2] = (TextView) getView().findViewById(R.id.monday2);
        monday[3] = (TextView) getView().findViewById(R.id.monday3);
        monday[4] = (TextView) getView().findViewById(R.id.monday4);
        monday[5] = (TextView) getView().findViewById(R.id.monday5);
        monday[6] = (TextView) getView().findViewById(R.id.monday6);
        monday[7] = (TextView) getView().findViewById(R.id.monday7);
        monday[8] = (TextView) getView().findViewById(R.id.monday8);
        monday[9] = (TextView) getView().findViewById(R.id.monday9);
        monday[10] = (TextView) getView().findViewById(R.id.monday10);
        monday[11] = (TextView) getView().findViewById(R.id.monday11);

        tuesday[0] = (TextView) getView().findViewById(R.id.tuesday0);
        tuesday[1] = (TextView) getView().findViewById(R.id.tuesday1);
        tuesday[2] = (TextView) getView().findViewById(R.id.tuesday2);
        tuesday[3] = (TextView) getView().findViewById(R.id.tuesday3);
        tuesday[4] = (TextView) getView().findViewById(R.id.tuesday4);
        tuesday[5] = (TextView) getView().findViewById(R.id.tuesday5);
        tuesday[6] = (TextView) getView().findViewById(R.id.tuesday6);
        tuesday[7] = (TextView) getView().findViewById(R.id.tuesday7);
        tuesday[8] = (TextView) getView().findViewById(R.id.tuesday8);
        tuesday[9] = (TextView) getView().findViewById(R.id.tuesday9);
        tuesday[10] = (TextView) getView().findViewById(R.id.tuesday10);
        tuesday[11] = (TextView) getView().findViewById(R.id.tuesday11);

        wednesday[0] = (TextView) getView().findViewById(R.id.wednesday0);
        wednesday[1] = (TextView) getView().findViewById(R.id.wednesday1);
        wednesday[2] = (TextView) getView().findViewById(R.id.wednesday2);
        wednesday[3] = (TextView) getView().findViewById(R.id.wednesday3);
        wednesday[4] = (TextView) getView().findViewById(R.id.wednesday4);
        wednesday[5] = (TextView) getView().findViewById(R.id.wednesday5);
        wednesday[6] = (TextView) getView().findViewById(R.id.wednesday6);
        wednesday[7] = (TextView) getView().findViewById(R.id.wednesday7);
        wednesday[8] = (TextView) getView().findViewById(R.id.wednesday8);
        wednesday[9] = (TextView) getView().findViewById(R.id.wednesday9);
        wednesday[10] = (TextView) getView().findViewById(R.id.wednesday10);
        wednesday[11] = (TextView) getView().findViewById(R.id.wednesday11);

        thursday[0] = (TextView) getView().findViewById(R.id.thursday0);
        thursday[1] = (TextView) getView().findViewById(R.id.thursday1);
        thursday[2] = (TextView) getView().findViewById(R.id.thursday2);
        thursday[3] = (TextView) getView().findViewById(R.id.thursday3);
        thursday[4] = (TextView) getView().findViewById(R.id.thursday4);
        thursday[5] = (TextView) getView().findViewById(R.id.thursday5);
        thursday[6] = (TextView) getView().findViewById(R.id.thursday6);
        thursday[7] = (TextView) getView().findViewById(R.id.thursday7);
        thursday[8] = (TextView) getView().findViewById(R.id.thursday8);
        thursday[9] = (TextView) getView().findViewById(R.id.thursday9);
        thursday[10] = (TextView) getView().findViewById(R.id.thursday10);
        thursday[11] = (TextView) getView().findViewById(R.id.thursday11);

        friday[0] = (TextView) getView().findViewById(R.id.friday0);
        friday[1] = (TextView) getView().findViewById(R.id.friday1);
        friday[2] = (TextView) getView().findViewById(R.id.friday2);
        friday[3] = (TextView) getView().findViewById(R.id.friday3);
        friday[4] = (TextView) getView().findViewById(R.id.friday4);
        friday[5] = (TextView) getView().findViewById(R.id.friday5);
        friday[6] = (TextView) getView().findViewById(R.id.friday6);
        friday[7] = (TextView) getView().findViewById(R.id.friday7);
        friday[8] = (TextView) getView().findViewById(R.id.friday8);
        friday[9] = (TextView) getView().findViewById(R.id.friday9);
        friday[10] = (TextView) getView().findViewById(R.id.friday10);
        friday[11] = (TextView) getView().findViewById(R.id.friday11);

        saturday[0] = (TextView) getView().findViewById(R.id.saturday0);
        saturday[1] = (TextView) getView().findViewById(R.id.saturday1);
        saturday[2] = (TextView) getView().findViewById(R.id.saturday2);
        saturday[3] = (TextView) getView().findViewById(R.id.saturday3);
        saturday[4] = (TextView) getView().findViewById(R.id.saturday4);
        saturday[5] = (TextView) getView().findViewById(R.id.saturday5);
        saturday[6] = (TextView) getView().findViewById(R.id.saturday6);
        saturday[7] = (TextView) getView().findViewById(R.id.saturday7);
        saturday[8] = (TextView) getView().findViewById(R.id.saturday8);
        saturday[9] = (TextView) getView().findViewById(R.id.saturday9);
        saturday[10] = (TextView) getView().findViewById(R.id.saturday10);
        saturday[11] = (TextView) getView().findViewById(R.id.saturday11);

        new BackgroundTask().execute();
    }

    class BackgroundTask extends AsyncTask<Void, Void, String> {
            String target;

            @Override
            protected void onPreExecute() {
                try {
                target = "http://172.30.1.4/ScheduleList.php?u_id="+ URLEncoder.encode(MainActivity.u_id, "UTF-8");
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
            }

            @Override
            protected String doInBackground(Void... voids) {
                try {
                    URL url = new URL(target);
                    HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                    InputStream inputStream = httpURLConnection.getInputStream();
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                    String temp;
                    StringBuilder stringBuilder = new StringBuilder();
                    while ((temp = bufferedReader.readLine()) != null)
                    {
                        stringBuilder.append(temp + "\n");
                    }
                    bufferedReader.close();
                    inputStream.close();
                    httpURLConnection.disconnect();
                    return stringBuilder.toString().trim();
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
                return null;
            }

            @Override
            public void onProgressUpdate (Void... values) {
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
                    int courseID;

                    while (count < jsonArray.length())
                    {
                        JSONObject object =jsonArray.getJSONObject(count);
                        courseID = object.getInt("courseID");
                        courseProfessor = object.getString("courseProfessor");
                        courseTime = object.getString("courseTime");
                        courseTitle = object.getString("courseTitle");

                        schedule.addSchedule(courseTime, courseTitle, courseProfessor);
                        count++;
                    }

                }
                catch (Exception e) {
                    e.printStackTrace();
                }
                schedule.setting(monday, tuesday, wednesday, thursday, friday, saturday, getContext());
            }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_statics, container, false);
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }



    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}