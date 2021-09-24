package com.example.attendance;
import android.app.PendingIntent;
import android.content.Intent;
import android.nfc.NfcAdapter;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;

public class NfcActivity extends AppCompatActivity {

    private static final String TAG ="" ;
    private TextView myUID;
    private NfcAdapter nfcAdapter;
    private static String tagNum = null;
    private PendingIntent pendingIntent;
    long mNow;
    Date mDate;
    SimpleDateFormat mFormat = new SimpleDateFormat("mmss");

    private String time1="";
    private int time2=0;

    TextView mTextView;
    int count=0, home=0;
    private boolean lock=false;
    public static boolean absent=true;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nfc);
        myUID = (TextView) findViewById(R.id.myUID);
        nfcAdapter = NfcAdapter.getDefaultAdapter(this);
        Intent intent = new Intent(this, getClass()).addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);
        mTextView = (TextView) findViewById(R.id.timer);
        lock=true;
        Thread t = new Thread(){
            public void run()
            {
                try {
                    while (!isInterrupted()) {
                        Thread.sleep(1000);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                getTime2();
                            }
                        });
                    }
                } catch (InterruptedException e) {
                }
            }
        };
        t.start();
    }
    private String getTime(){
        mNow = System.currentTimeMillis();
        mDate = new Date(mNow);
        return mFormat.format(mDate);
    }
    private void getTime2(){
        mNow = System.currentTimeMillis();
        mDate = new Date(mNow);
        SimpleDateFormat gettime = new SimpleDateFormat("현재 시간 : yyyy년 MM월 dd일 hh시 mm분 ss초");
        String time=gettime.format(mDate);
        mTextView.setText(time);
    }
    @Override
    protected void onPause() {
        if (nfcAdapter != null) {
            nfcAdapter.disableForegroundDispatch(this);
        }
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (nfcAdapter != null) {
            nfcAdapter.enableForegroundDispatch(this, pendingIntent, null, null);
        }
    }
    @Override
    public void onBackPressed() {  // 뒤로가기 true 뒤로가기 됨 false가 안됨
        if (lock==true)
        {
            super.onBackPressed();
        }
    }

    @Override
    protected void onNewIntent(final Intent intent) {
        if (intent.getAction().equals(NfcAdapter.ACTION_TAG_DISCOVERED))
        {
            myUID = (TextView) findViewById(R.id.myUID);
            myUID.setText("태그 번호 : "+ByteArrayToHexString(intent.getByteArrayExtra(NfcAdapter.EXTRA_ID)));
            AlertDialog.Builder builder = new AlertDialog.Builder(NfcActivity.this);
            builder.setMessage("태그확인").setPositiveButton("확인",null)
                    .create().show();
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN); //상단바제거
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE); //화면잠금
            lock =false; //뒤로가기 잠김
            home=1;
            count=0;
            absent=false; //결석이 아님

        }
        time1 =  mFormat.format(mDate);
        time2 = Integer.parseInt(time1);

        if (time2>=1600 && time2<=1800){
            Toast.makeText(getApplicationContext(), "잠금 해제", Toast.LENGTH_LONG).show();
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE); //화면잠금 해제
            lock=true;
            home=0;
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if(home==1)             //태그를 찍고서 홈 버튼 눌렀을 때
        {
            Toast.makeText(this, "홈 버튼", Toast.LENGTH_LONG).show();
            finish();
            Intent i = new Intent(NfcActivity. this, NfcActivity.class);
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(i);
            home=2;
            Response.Listener<String> responseListener = new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    try
                    {
                        JSONObject jsonResponse = new JSONObject(response);
                        boolean success = jsonResponse.getBoolean("success"); //true
                        if (success)
                        {
                            AlertDialog.Builder builder = new AlertDialog.Builder(NfcActivity.this);
                            builder.setMessage("사용 가능한 학번입니다.").setNegativeButton("확인",null)
                                    .create().show();
                        }
                        else
                        {
                            AlertDialog.Builder builder = new AlertDialog.Builder(NfcActivity.this);
                            builder.setMessage("이미 가입된 회원입니다.").setPositiveButton("다시 시도",null)
                                .create().show();
                        }
                    }
                    catch (JSONException e)
                    {
                        e.printStackTrace();
                    }
                }
            };
            deleteCheck deleteCheck = new deleteCheck(MainActivity.u_id, responseListener);
            RequestQueue queue = Volley.newRequestQueue(NfcActivity.this);
            queue.add(deleteCheck);
        }
    }
    private String ByteArrayToHexString(byte [] array) {
        int i, j, in;
        String [] hex = {"0","1","2","3","4","5","6","7","8","9","A","B","C","D","E","F"};
        String out = "";

        for(j=0; j< array.length ; ++j) {
            in = (int) array[j] & 0xFF;
            i = (in >> 4) & 0x0f;
            out += hex[i];
            i = in & 0x0f;
            out += hex[i];
        }
        return out;
    }

}

