package com.example.attendance;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.android.volley.RequestQueue;

public class SettingActivity extends AppCompatActivity {
    AlertDialog dialog;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        Button delete = (Button) findViewById(R.id.delete);

        final EditText idText = (EditText) findViewById(R.id.idText);

        final String u_id = MainActivity.u_id;
        TextView textView1 = (TextView) findViewById(R.id.u_id); //R.id.setting.xml 변수임
        textView1.setText("학번: " + u_id);

        final String u_pw = MainActivity.u_pw;
        TextView textView2 = (TextView) findViewById(R.id.u_pw);
        textView2.setText("비밀번호: " + u_pw);

        String major = MainActivity.major;
        TextView textView3 = (TextView) findViewById(R.id.major);
        textView3.setText("전공: " + major);

        String job = MainActivity.job;
        TextView textView4 = (TextView) findViewById(R.id.job);
        textView4.setText("Professor/Student: " + job);

        String u_name = MainActivity.u_name;
        TextView textView5 = (TextView) findViewById(R.id.u_name);
        textView5.setText("이름: " + u_name);

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {     // 회원탈퇴 버튼
                final String u_id = MainActivity.u_id;
                AlertDialog.Builder builder = new AlertDialog.Builder(SettingActivity.this);
                dialog = builder.setMessage("정말 회원을 탈퇴하시겠습니까?")
                        .setPositiveButton("취소", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialog.cancel();
                            }
                        })
                        .setNegativeButton("탈퇴", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Response.Listener<String> responseLister = new Response.Listener<String>() {
                                    @Override
                                    public void onResponse(String response) {
                                        try
                                        {

                                        } catch (Exception e)
                                        {
                                            e.printStackTrace();
                                        }

                                    }
                                };
                                DeleteRequest DeleteRequest = new DeleteRequest(u_id, responseLister);
                                RequestQueue queue = Volley.newRequestQueue(SettingActivity.this);
                                queue.add(DeleteRequest);
                                Toast.makeText(getApplicationContext(),"회원 탈퇴 완료",Toast.LENGTH_LONG).show();
                                Intent intent = new Intent(SettingActivity.this, LoginActivity.class);
                                SettingActivity.this.startActivity(intent);
                                finish();
                            }
                        })
                        .create();
                dialog.show();
            }
        });



    }
}