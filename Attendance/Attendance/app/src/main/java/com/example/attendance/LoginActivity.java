package com.example.attendance;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity {
    private AlertDialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final EditText idText = (EditText)findViewById(R.id.idText);
        final EditText passwordText = (EditText)findViewById(R.id.passwordText);
        final RadioButton loginprofessor = (RadioButton) findViewById(R.id.loginprofessor);
        final RadioButton loginstudent = (RadioButton) findViewById(R.id.loginstudent);

        Button registerButton = (Button) findViewById(R.id.registerButton);
        Button loginButton = (Button) findViewById(R.id.loginButton);

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent registerIntent = new Intent(LoginActivity.this, RegisterActivity.class);
                LoginActivity.this.startActivity(registerIntent);
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                final String u_id = idText.getText().toString();
                final String u_pw = passwordText.getText().toString();
                final String job= loginprofessor.getText().toString();

                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try{
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");
                            if(success && loginstudent.isChecked() && u_id.getBytes().length ==9)
                            {
                                String u_id = jsonResponse.getString("u_id");
                                String u_pw = jsonResponse.getString("u_pw");
                                String major = jsonResponse.getString("major");
                                String job = jsonResponse.getString("job");
                                String u_name = jsonResponse.getString("u_name");

                                AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
                                builder.setMessage("학생 로그인에 성공했습니다.").setPositiveButton("확인",null)
                                        .create().show();
                                Intent loginIntent = new Intent(LoginActivity.this , MainActivity.class);
                                //Intent setIntent = new Intent(LoginActivity.this,SettingActivity.class);
                                loginIntent.putExtra("u_id",u_id);
                                loginIntent.putExtra("u_pw",u_pw);
                                loginIntent.putExtra("major",major);
                                loginIntent.putExtra("job",job);
                                loginIntent.putExtra("u_name",u_name);
                                LoginActivity.this.startActivity(loginIntent);
                            }

                            if(success && loginprofessor.isChecked() && u_id.getBytes().length <=8)
                            {
                                String prou_id = jsonResponse.getString("u_id");
                                String prou_pw = jsonResponse.getString("u_pw");
                                String promajor = jsonResponse.getString("major");
                                String projob = jsonResponse.getString("job");
                                String proname = jsonResponse.getString("u_name");


                                AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
                                builder.setMessage("교수 로그인에 성공했습니다.").setPositiveButton("확인",null)
                                        .create().show();
                                Intent loginIntent2 = new Intent(LoginActivity.this , proMainActivity.class);
                                //Intent setIntent = new Intent(LoginActivity.this,SettingActivity.class);
                                loginIntent2.putExtra("u_id",prou_id);
                                loginIntent2.putExtra("u_pw",prou_pw);
                                loginIntent2.putExtra("major",promajor);
                                loginIntent2.putExtra("job",projob);
                                loginIntent2.putExtra("u_name",proname);
                                LoginActivity.this.startActivity(loginIntent2);
                            }
                            else if(!success || u_id.getBytes().length <=0  || u_pw.getBytes().length <=0 ||(u_id.getBytes().length <=0 && u_pw.getBytes().length <=0))
                            {
                                AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
                                builder.setMessage("로그인에 실패하였습니다.")
                                        .setNegativeButton("다시 시도", null)
                                        .create()
                                        .show();
                            }
                        } catch (Exception e)
                        {
                            e.printStackTrace();
                        }
                    }
                };
                LoginRequest loginRequest = new LoginRequest(u_id,u_pw,responseListener);
                RequestQueue queue = Volley.newRequestQueue(LoginActivity.this);
                queue.add(loginRequest);

            }
        });
    }
}