package com.example.attendance;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class proMainActivity extends AppCompatActivity {

    public static String prou_id;
    public static String prou_pw;
    public static String promajor;
    public static String projob;
    public static String proname;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pro_main);

        final EditText idText = (EditText) findViewById(R.id.idText);
        prou_id = getIntent().getStringExtra("u_id");
        prou_pw = getIntent().getStringExtra("u_pw");
        promajor = getIntent().getStringExtra("major");
        projob = getIntent().getStringExtra("job");
        proname = getIntent().getStringExtra("u_name");


        TextView textView1 = (TextView) findViewById(R.id.prou_id); //R.id.pro_main.xml 변수임
        textView1.setText("학번: " + prou_id);


        TextView textView2 = (TextView) findViewById(R.id.prou_pw);
        textView2.setText("비밀번호: " + prou_pw);


        TextView textView3 = (TextView) findViewById(R.id.promajor);
        textView3.setText("전공: " + promajor);


        TextView textView4 = (TextView) findViewById(R.id.projob);
        textView4.setText("Professor/Student: " + projob);

        TextView textView5 = (TextView) findViewById(R.id.proname);
        textView5.setText("이름: " + proname);

        Button promanage = (Button) findViewById(R.id.promanage);

        promanage.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent checkIntent = new Intent(proMainActivity.this, proManageActivity.class);
                startActivity(checkIntent);
            }
        });
    }
}