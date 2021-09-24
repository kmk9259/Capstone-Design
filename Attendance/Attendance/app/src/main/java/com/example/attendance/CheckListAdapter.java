package com.example.attendance;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
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


public class CheckListAdapter extends BaseAdapter{

    private Context context;
    private List<Check> checkList;
    long mNow;
    Date mDate;
    SimpleDateFormat mFormat = new SimpleDateFormat("mmss");
    private String time1="" ,three="결석";
    private int time2=0;
    public int class_check1=0;

    public CheckListAdapter(Context context, List<Check> checkList) {
        this.context = context;
        this.checkList = checkList;
    }

    @Override
    public int getCount() {
        return checkList.size();
    }

    @Override
    public Object getItem(int i) {
        return checkList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        View v = View.inflate(context, R.layout.check, null);
        TextView checkTitle = (TextView) v.findViewById(R.id.checkTitle);
        TextView checkProfessor = (TextView) v.findViewById(R.id.checkProfessor);
        TextView checkTime = (TextView) v.findViewById(R.id.checkTime);
        TextView checkRoom = (TextView) v.findViewById(R.id.checkRoom);

        checkTitle.setText("강의명 : " + checkList.get(i).getCourseTitle());
        checkProfessor.setText("교수님 : " +checkList.get(i).getCourseProfessor());
        checkTime.setText("시간 : " +checkList.get(i).getCourseTime());
        checkRoom.setText("강의실 : " +checkList.get(i).getCourseRoom());

        v.setTag(checkList.get(i).getCourseTitle());

        Button checkButton = (Button) v.findViewById(R.id.checkButton);
        checkButton.setOnClickListener(new Button.OnClickListener()
        {
            public void onClick(View v)
            {
                context.startActivity(new Intent(context, NfcActivity.class));
                Toast.makeText(context, getTime(), Toast.LENGTH_LONG).show();
                time1 =  mFormat.format(mDate);
                time2 = Integer.parseInt(time1);
                String Check_time = getTime2();     //출석완료  시간

                if (time2>=0000 && time2<=1059)
                { //0분 ~10분 : 출석 인정
                    class_check1++;
                    three=class_check1+"교시 출석";

                }
                if(time2 >= 1100 && time2<=4959)
                {
                    class_check1++;
                    three=class_check1+"교시 지각";
                }
                if(class_check1==3)
                {
                    class_check1=0;
                }
                    Response.Listener<String> responseListener = new Response.Listener<String>(){
                        @Override
                        public void onResponse(String response) {
                            try
                            {
                                JSONObject jsonResponse = new JSONObject(response);
                                boolean success = jsonResponse.getBoolean("success");
                                if(success)
                                {

                                    AlertDialog.Builder builder = new AlertDialog.Builder(context);
                                    AlertDialog dialog = builder.setMessage(three)
                                            .setPositiveButton("확인", null)
                                            .create();
                                    dialog.show();
                                }
                                else
                                {
                                    AlertDialog.Builder builder = new AlertDialog.Builder(context);
                                    AlertDialog dialog = builder.setMessage("실패")
                                            .setPositiveButton("확인", null)
                                            .create();
                                    dialog.show();
                                }
                            }
                            catch (Exception e)
                            {
                                e.printStackTrace();
                            }
                        }
                    };
                    NfcRequest nfcRequest = new NfcRequest(MainActivity.u_id, checkList.get(i).getCourseTitle() + "" ,three, Check_time, responseListener);
                    RequestQueue queue = Volley.newRequestQueue(context);
                    queue.add(nfcRequest);

            }
        });

        return v;
    }
    private String getTime(){
        mNow = System.currentTimeMillis();
        mDate = new Date(mNow);
        return mFormat.format(mDate);
    }
    private String getTime2(){
        mNow = System.currentTimeMillis();
        mDate = new Date(mNow);
        SimpleDateFormat gettime = new SimpleDateFormat("yyyyMdd");
        return gettime.format(mDate);
    }
}
