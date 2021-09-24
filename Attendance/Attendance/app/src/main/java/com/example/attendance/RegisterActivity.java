package com.example.attendance;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class RegisterActivity extends AppCompatActivity
{
    private AlertDialog dialog;
    private boolean validate = false;
    private boolean validate2 = false;
    private boolean use = false;
    private boolean equal;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        final EditText idText = (EditText)findViewById(R.id.idText);
        final EditText passwordText = (EditText)findViewById(R.id.passwordText);
        final EditText passwordCheck = (EditText)findViewById(R.id.passwordCheck);
        final EditText majorChoice = (EditText)findViewById(R.id.major);
        final EditText u_nameText = (EditText)findViewById(R.id.u_name);
        final ImageView setImage = (ImageView)findViewById(R.id.setImage);
        final RadioButton professor = (RadioButton) findViewById(R.id.professor);
        final Button registerButton = (Button)findViewById(R.id.registerButton);
        final Button schoolsCheck = (Button)findViewById(R.id.schoolsCheck);
        final Button idCheckButton = (Button)findViewById(R.id.idCheckButton);

        schoolsCheck.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                final String u_id = idText.getText().toString();
                if(validate)
                {
                    return;
                }
                if(u_id.equals(""))
                {
                    AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
                    builder.setMessage("학번이 빈 칸입니다.")
                            .setPositiveButton("OK", null)
                            .create().show();
                    return;
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
                                AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
                                builder.setMessage("학교 인증이 불가능합니다.")
                                        .setNegativeButton("확인", null)
                                        .create().show();
                            }
                            else
                            {
                                AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
                                builder.setMessage(u_id+"님, 상명대학교 인증이 완료되었습니다.")
                                        .setPositiveButton("확인", null)
                                        .create().show();
                                //idText.setEnabled(false);//아이디값을 바꿀 수 없도록 함
                                //schoolsCheck.setBackgroundColor(Color.parseColor("#A6A6A6"));
                                validate = true;//검증완료
                                validate2=true;

                            }
                        }
                        catch (Exception e)
                        {
                            e.printStackTrace();
                        }
                    }
                };
                ValidateRequest validateRequest = new ValidateRequest(u_id, responseListener);
                RequestQueue queue = Volley.newRequestQueue(RegisterActivity.this);
                queue.add(validateRequest);
            }
        });
        ///////////////////////비밀번호 확인 db////////////////////
        passwordCheck.addTextChangedListener(new TextWatcher()
        {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(passwordText.getText().toString().equals(passwordCheck.getText().toString())) {
                    setImage.setImageResource(R.drawable.checked);
                } else
                {
                    setImage.setImageResource(R.drawable.wrong);
                }
            }
            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        //////////////////학번 중복체크///////////////////////
        idCheckButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String u_id = idText.getText().toString();


                if (!validate2)
                {
                    AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
                    builder.setMessage("학교 인증을 먼저 해주세요")
                            .setNegativeButton("확인", null)
                            .create().show();
                    return;
                }
                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try
                        {
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success"); //true
                            if (success)
                            {
                                AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
                                builder.setMessage("사용 가능한 학번입니다.").setNegativeButton("확인",null)
                                        .create().show();
                                use = true;
                                idText.setEnabled(false);//아이디값을 바꿀 수 없도록 함


                            }
                            else
                            {
                                AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
                                builder.setMessage("이미 가입된 회원입니다.").setPositiveButton("다시 시도",null)
                                        .create().show();
                                validate=false;

                            }
                        }
                        catch (JSONException e)
                        {
                            e.printStackTrace();
                        }
                    }
                };
                idCheckRequest idCheckRequest = new idCheckRequest(u_id,responseListener);
                RequestQueue queue = Volley.newRequestQueue(RegisterActivity.this);
                queue.add(idCheckRequest);
            }
        });

        ///////////////////////가입버튼 누르기 db////////////////////
        registerButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                String u_id = idText.getText().toString();
                String u_pw = passwordText.getText().toString();
                String major = majorChoice.getText().toString();
                String pwCheck = passwordCheck.getText().toString();
                String u_name = u_nameText.getText().toString();
                String job;
                equal = u_id.equals("")|| u_pw.equals("") || major.equals("") || pwCheck.equals("");

                if(professor.isChecked())
                    job="Professor";
                else job = "Student";
                if(!validate) //false
                {
                    AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
                    builder.setMessage("학교 인증을 먼저 해주세요.")
                            .setNegativeButton("확인", null)
                            .create().show();
                    return;
                }
                if(equal)
                {
                    AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
                    builder.setMessage("빈 칸 없이 입력해주세요.")
                            .setNegativeButton("확인", null)
                            .create().show();
                    return;
                }
                else if (!equal && !use)
                {
                    AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
                    builder.setMessage("학번 중복 체크를 먼저 해주세요.")
                            .setNegativeButton("확인", null)
                            .create().show();
                    return;
                }
                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try
                        {
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success"); //true
                            if (success)
                            {
                                AlertDialog.Builder builder2 = new AlertDialog.Builder(RegisterActivity.this);
                                builder2.setMessage("회원 등록에 성공했습니다.").setPositiveButton("확인",null)
                                        .create().show();

                                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                                RegisterActivity.this.startActivity(intent);
                            }
                            else
                            {
                                AlertDialog.Builder builder2 = new AlertDialog.Builder(RegisterActivity.this);
                                builder2.setMessage("회원 등록에 실패했습니다.").setNegativeButton("다시 시도",null)
                                        .create().show();
                            }

                        }
                        catch (JSONException e)
                        {
                            e.printStackTrace();
                        }
                    }
                };
                RegisterRequest registerRequest = new RegisterRequest(u_id, u_pw,job,major,u_name,responseListener);
                RequestQueue queue = Volley.newRequestQueue(RegisterActivity.this);
                queue.add(registerRequest);

            }
        });

    }


}