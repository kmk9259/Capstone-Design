package com.example.attendance;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

class  LoginRequest extends StringRequest {
    final static private String URL = "http://172.30.1.4/login.php";
    private Map<String, String> parameters;

    public LoginRequest(String u_id, String u_pw,
                        Response.Listener<String> listener){

        super(Method.POST, URL, listener, null);
        try {

            parameters=new HashMap<>();
            parameters.put("u_id", u_id);
            parameters.put("u_pw", u_pw);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Override
    protected Map<String, String> getParams() {
        return parameters;
    }
}
class  RegisterRequest extends StringRequest {
    final static private String URL = "http://172.30.1.4/register.php";
    private Map<String, String> parameters;

    public RegisterRequest(String u_id, String u_pw,String job, String major, String u_name, Response.Listener<String> listener) {
        super(Method.POST, URL, listener, null);
        parameters = new HashMap<>();
        parameters.put("u_id",u_id);
        parameters.put("u_pw", u_pw);
        parameters.put("major", major);
        parameters.put("job", job);
        parameters.put("u_name", u_name);

    }
    @Override
    protected Map<String, String> getParams(){
        return parameters;
    }
}
class  ValidateRequest extends StringRequest
{
    final static private String URL = "http://172.30.1.4/confirm.php";
    private Map<String, String> parameters;

    public ValidateRequest(String u_id, Response.Listener<String> listener) {
        super(Method.POST, URL, listener, null);
        parameters = new HashMap<>();
        parameters.put("u_id",u_id);

    }
    public Map<String, String> getParams() {
        return parameters;
    }
}
class  DeleteRequest extends StringRequest
{
    final static private String URL = "http://172.30.1.4/delete.php";
    private Map<String, String> parameters;

    public DeleteRequest(String u_id, Response.Listener<String> listener) {
        super(Method.POST, URL, listener, null);
        parameters = new HashMap<>();
        parameters.put("u_id",u_id);
    }
    public Map<String, String> getParams() {
        return parameters;
    }
}



class idCheckRequest extends StringRequest {
    final static private String URL = "http://172.30.1.4/idCheck.php";
    private Map<String, String> parameters;

    public idCheckRequest(String u_id, Response.Listener<String> listener) {
        super(Method.POST, URL, listener, null);
        parameters = new HashMap<>();
        parameters.put("u_id",u_id);

    }
    @Override
    protected Map<String, String> getParams(){
        return parameters;
    }
}

class AddRequest extends StringRequest {

    final static private String URL = "http://172.30.1.4/CourseAdd.php";
    private Map<String, String> parameters;

    public AddRequest(String u_id, String courseID, Response.Listener<String> listener) {
        super(Method.POST, URL, listener, null);
        parameters = new HashMap<>();
        parameters.put("u_id",u_id);
        parameters.put("courseID", courseID);

    }
    @Override
    protected Map<String, String> getParams(){
        return parameters;
    }
}
class NfcRequest extends StringRequest {

    final static private String URL = "http://172.30.1.4/attend.php";
    private Map<String, String> parameters;

    public NfcRequest(String u_id, String courseTitle, String three, String Check_time, Response.Listener<String> listener) {
        super(Method.POST, URL, listener, null);
        parameters = new HashMap<>();
        parameters.put("u_id",u_id);
        parameters.put("courseTitle", courseTitle);
        parameters.put("three", three);
        parameters.put("Check_time", Check_time);
    }
    @Override
    protected Map<String, String> getParams(){
        return parameters;
    }
}

class ScheduleDeleteRequest extends StringRequest {

    final static private String URL = "http://172.30.1.4/ScheduleDelete.php";
    private Map<String, String> parameters;

    public ScheduleDeleteRequest(String u_id, String courseID, Response.Listener<String> listener) {
        super(Method.POST, URL, listener, null);
        parameters = new HashMap<>();
        parameters.put("u_id",u_id);
        parameters.put("courseID", courseID);

    }
    @Override
    protected Map<String, String> getParams(){
        return parameters;
    }
}
class deleteCheck extends StringRequest {

    final static private String URL = "http://172.30.1.4/deleteCheck.php";
    private Map<String, String> parameters;

    public deleteCheck(String u_id, Response.Listener<String> listener) {
        super(Method.POST, URL, listener, null);
        parameters = new HashMap<>();
        parameters.put("u_id",u_id);

    }
    @Override
    protected Map<String, String> getParams(){
        return parameters;
    }
}
class manageRequest extends StringRequest {
    final static private String URL = "http://172.30.1.4/ManageUpdate.php";
    private Map<String, String> parameters;

    public manageRequest(String u_name, String courseTitle, String three, String temp,  Response.Listener<String> listener) {
        super(Method.POST, URL, listener, null);
        parameters = new HashMap<>();
        parameters.put("u_name",u_name);
        parameters.put("courseTitle",courseTitle);
        parameters.put("three",three);
        parameters.put("temp",temp);

    }
    @Override
    protected Map<String, String> getParams(){
        return parameters;
    }
}