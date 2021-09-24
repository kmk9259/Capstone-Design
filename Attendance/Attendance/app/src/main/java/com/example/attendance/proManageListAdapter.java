package com.example.attendance;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.List;

public class proManageListAdapter extends BaseAdapter{

    private Context context;
    private List<proManage> proManageList;
    private String temp="";

    public proManageListAdapter(Context context, List<proManage> proManageList) {
        this.context = context;
        this.proManageList = proManageList;
    }
    @Override
    public int getCount() {
        return proManageList.size();
    }

    @Override
    public Object getItem(int i) {
        return proManageList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        final View v = View.inflate(context, R.layout.promanage, null);

        Button proManage = (Button) v.findViewById(R.id.proManage);
        TextView proTitle = (TextView) v.findViewById(R.id.proTitle);
        TextView proName = (TextView) v.findViewById(R.id.proName);
        TextView proTime = (TextView) v.findViewById(R.id.proTime);
        TextView proRoom = (TextView) v.findViewById(R.id.proRoom);
        TextView proCheck_time = (TextView) v.findViewById(R.id.proCheck_time);
        final RadioButton radioButton = (RadioButton)v.findViewById(R.id.radioButton);
        final RadioButton radioButton2 = (RadioButton)v.findViewById(R.id.radioButton2);
        final RadioButton radioButton3 = (RadioButton)v.findViewById(R.id.radioButton3);
        RadioGroup rg = (RadioGroup)v.findViewById(R.id.radioGroup);

        proTitle.setText("강의명: " + proManageList.get(i).getCourseTitle());
        proName.setText("학생: " +proManageList.get(i).getU_name());
        proTime.setText("강의시간: " +proManageList.get(i).getCourseTime());
        proRoom.setText("강의실: " +proManageList.get(i).getCourseRoom());
        proCheck_time.setText("출석 완료 시간: " +proManageList.get(i).getCheck_time());

        proManage.setText(proManageList.get(i).getThree());
        v.setTag(proManageList.get(i).getCourseTitle());

        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId)
            {
                //1교시 관리
                if(proManageList.get(i).getThree().equals("1교시 출석"))
                {
                    if (radioButton.isChecked())
                    {
                        temp = "1교시 출석";
                    }
                    if(radioButton2.isChecked())
                    {
                        temp="1교시 지각";
                    }
                    if (radioButton3.isChecked()) {
                        temp = "1교시 결석";
                    }
                }
                if(proManageList.get(i).getThree().equals("1교시 지각"))
                {
                    if (radioButton.isChecked())
                    {
                        temp = "1교시 출석";
                    }
                    if(radioButton2.isChecked())
                    {
                        temp="1교시 지각";
                    }
                    if (radioButton3.isChecked()) {
                        temp = "1교시 결석";
                    }
                }
                if(proManageList.get(i).getThree().equals("1교시 결석"))
                {
                    if (radioButton.isChecked())
                    {
                        temp = "1교시 출석";
                    }
                    if(radioButton2.isChecked())
                    {
                        temp="1교시 지각";
                    }
                    if (radioButton3.isChecked()) {
                        temp = "1교시 결석";
                    }
                }

                //2교시 관리
                if(proManageList.get(i).getThree().equals("2교시 출석"))
                {
                    if (radioButton.isChecked())
                    {
                        temp="2교시 출석";
                    }
                    if(radioButton2.isChecked())
                    {
                        temp="2교시 지각";
                    }
                    if (radioButton3.isChecked()) {
                        temp = "2교시 결석";
                    }
                }
                if(proManageList.get(i).getThree().equals("2교시 지각"))
                {
                    if (radioButton.isChecked())
                    {
                        temp="2교시 출석";
                    }
                    if(radioButton2.isChecked())
                    {
                        temp="2교시 지각";
                    }
                    if (radioButton3.isChecked()) {
                        temp = "2교시 결석";
                    }
                }
                if(proManageList.get(i).getThree().equals("2교시 결석"))
                {
                    if (radioButton.isChecked())
                    {
                        temp="2교시 출석";
                    }
                    if(radioButton2.isChecked())
                    {
                        temp="2교시 지각";
                    }
                    if (radioButton3.isChecked()) {
                        temp = "2교시 결석";
                    }
                }

                //3교시 관리
                if(proManageList.get(i).getThree().equals("3교시 출석"))
                {
                    if (radioButton.isChecked())
                    {
                        temp="3교시 출석";
                    }
                    if(radioButton2.isChecked())
                    {
                        temp="3교시 지각";
                    }
                    if (radioButton3.isChecked()) {
                        temp = "3교시 결석";
                    }
                }
                if(proManageList.get(i).getThree().equals("3교시 지각"))
                {
                    if (radioButton.isChecked())
                    {
                        temp="3교시 출석";
                    }
                    if(radioButton2.isChecked())
                    {
                        temp="3교시 지각";
                    }
                    if (radioButton3.isChecked()) {
                        temp = "3교시 결석";
                    }
                }
                if(proManageList.get(i).getThree().equals("3교시 결석"))
                {
                    if (radioButton.isChecked())
                    {
                        temp="3교시 출석";
                    }
                    if(radioButton2.isChecked())
                    {
                        temp="3교시 지각";
                    }
                    if (radioButton3.isChecked()) {
                        temp = "3교시 결석";
                    }
                }
            }
        });

        proManage.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v)
            {
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
                                AlertDialog dialog = builder.setMessage("성공")
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
                manageRequest manageRequest = new manageRequest(proManageList.get(i).getU_name(), proManageList.get(i).getCourseTitle(), proManageList.get(i).getThree(), temp, responseListener);
                RequestQueue queue = Volley.newRequestQueue(context);
                queue.add(manageRequest);
                context.startActivity(new Intent(context, proManageActivity.class));

            }

        });

        return v;
    }


}