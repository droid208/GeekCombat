package com.example.manyamadan.steroids;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class GitDetailActivity extends AppCompatActivity {

    TextView nameView,bioView,locationView,emailView;
    String url;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_git_detail);
        nameView=(TextView)findViewById(R.id.textView);
        emailView=(TextView)findViewById(R.id.textView2);
        bioView=(TextView)findViewById(R.id.textView3);
        locationView=(TextView)findViewById(R.id.textView4);

        url=getIntent().getStringExtra("url");
        getRe(url);

    }

    public void getRe(String url){
        StringRequest request=new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                pars(s);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Log.d("mytag",volleyError.toString());
            }
        });
        Volley.newRequestQueue(GitDetailActivity.this).add(request);
    }

    private void pars(String s){
        JSONObject object=null;
        try{
            object=new JSONObject(s);
            nameView.setText(object.getString("name"));
            emailView.setText(object.getString("email"));
            bioView.setText(object.getString("bio"));
            locationView.setText(object.getString("location"));
        }
        catch (JSONException e){
            Log.d("mytag",e.toString());
        }
    }
}
