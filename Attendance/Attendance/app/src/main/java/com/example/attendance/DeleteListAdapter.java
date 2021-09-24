package com.example.attendance;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
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

import java.util.List;

import static com.example.attendance.MainActivity.u_id;

public class DeleteListAdapter extends BaseAdapter{

        private Context context;
        private List<Delete> deleteList;
        private Fragment parent;

    public DeleteListAdapter(Context context, List<Delete> deleteList,  Fragment parent) {
        this.context = context;
        this.deleteList = deleteList;
        this.parent = parent;
    }
    @Override
    public int getCount() {
        return deleteList.size();
    }

    @Override
    public Object getItem(int i) {
        return deleteList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        View v = View.inflate(context, R.layout.delete, null);
        TextView deleteTitle = (TextView) v.findViewById(R.id.deleteTitle);
        TextView deleteProfessor = (TextView) v.findViewById(R.id.deleteProfessor);
        TextView deleteTime = (TextView) v.findViewById(R.id.deleteTime);
        TextView deleteRoom = (TextView) v.findViewById(R.id.deleteRoom);

        deleteTitle.setText("강의명 : " + deleteList.get(i).getCourseTitle());
        deleteProfessor.setText("교수님 : " +deleteList.get(i).getCourseProfessor());
        deleteTime.setText("시간 : " +deleteList.get(i).getCourseTime());
        deleteRoom.setText("강의실 : " +deleteList.get(i).getCourseRoom());

        v.setTag(deleteList.get(i).getCourseID());

        Button deleteButton = (Button) v.findViewById(R.id.deleteButton);
        deleteButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Response.Listener<String> responseListener = new Response.Listener<String>(){

                    @Override
                    public void onResponse(String response) {
                        try
                        {
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");
                            if(success)
                            {
                                AlertDialog.Builder builder = new AlertDialog.Builder(parent.getActivity());
                                AlertDialog dialog = builder.setMessage("강의 삭제")
                                        .setPositiveButton("확인", null)
                                        .create();
                                dialog.show();
                                deleteList.remove(i);
                                notifyDataSetChanged();
                            }
                            else
                            {
                                AlertDialog.Builder builder = new AlertDialog.Builder(parent.getActivity());
                                AlertDialog dialog = builder.setMessage("강의 삭제 실패")
                                        .setPositiveButton("다시", null)
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
                ScheduleDeleteRequest scheduleDeleteRequest = new ScheduleDeleteRequest(u_id, deleteList.get(i).getCourseID() + "", responseListener);
                RequestQueue queue = Volley.newRequestQueue(parent.getActivity());
                queue.add(scheduleDeleteRequest);
                //Toast.makeText(context, "gg"+deleteList.get(i).getCourseID(), Toast.LENGTH_LONG).show();
            }
        });
        return v;
    }

}
