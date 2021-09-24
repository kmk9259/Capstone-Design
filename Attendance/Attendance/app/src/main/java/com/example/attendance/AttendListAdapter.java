package com.example.attendance;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


public class AttendListAdapter extends BaseAdapter{

    private Context context;
    private List<Attend> attendList;

    public AttendListAdapter(Context context, List<Attend> attendList) {
        this.context = context;
        this.attendList = attendList;
    }
    @Override
    public int getCount() {
        return attendList.size();
    }

    @Override
    public Object getItem(int i) {
        return attendList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        View v = View.inflate(context, R.layout.attend, null);
        TextView attendTitle = (TextView) v.findViewById(R.id.attendTitle);
        TextView attendProfessor = (TextView) v.findViewById(R.id.attendProfessor);
        TextView attendTime = (TextView) v.findViewById(R.id.attendTime);
        TextView attendRoom = (TextView) v.findViewById(R.id.attendRoom);
        TextView attend = (TextView) v.findViewById(R.id.attend);
        TextView Check_time = (TextView) v.findViewById(R.id.Check_time);

        attendTitle.setText("강의명 : " + attendList.get(i).getCourseTitle());
        attendProfessor.setText("교수님 : " +attendList.get(i).getCourseProfessor());
        attendTime.setText("시간 : " +attendList.get(i).getCourseTime());
        attendRoom.setText("강의실 : " +attendList.get(i).getCourseRoom());
        Check_time.setText("출석 완료 시간 : " +attendList.get(i).getCheck_time());   //출석완료시간

        attend.setText(attendList.get(i).getThree());
        v.setTag(attendList.get(i).getCourseTitle());

        return v;
    }

}