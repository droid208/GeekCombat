package com.example.manyamadan.steroids;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by manyamadan on 17/07/16.
 */

public class Git extends AppCompatActivity {
    SharedPreferences preferences;
    private List<GitModel> gitModelList ;
    public static ListView list_ordrs;
    Git_adapter adapter;
    public static  String post;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.git_layout);
        preferences= PreferenceManager.getDefaultSharedPreferences(this);
        list_ordrs = (ListView) findViewById(R.id.list);
        gitModelList=new ArrayList<>();

        adapter=new Git_adapter(this,gitModelList);

        list_ordrs.setAdapter(adapter);
        list_ordrs.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                GitModel temp = gitModelList.get(position);
                Intent intent = new Intent(Git.this, GitDetailActivity.class);
                intent.putExtra("url", temp.getUrl());

                startActivity(intent);

            }
        });
    }
    public void getRe(String url){
        StringRequest request=new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                pars(s);
                Log.d("mytag",s);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Log.d("mytag",volleyError.toString());
            }
        });
        Volley.newRequestQueue(Git.this).add(request);
    }

    private void pars(String s){
        JSONObject object=null;
        try{
            gitModelList.clear();
            object=new JSONObject(s);
            JSONArray array=object.getJSONArray("items");
            for(int i=0;i<array.length();i++){
                JSONObject object1=array.getJSONObject(i);
                JSONObject ownerObj=object1.getJSONObject("owner");
                GitModel gitModel=new GitModel();
                gitModel.setUrl(ownerObj.getString("url"));
                gitModel.setUser_id(ownerObj.getString("login"));
                gitModelList.add(gitModel);
            }
            adapter.notifyDataSetChanged();
        }
        catch (JSONException e){
            Log.d("mytag",e.toString());
        }
    }

    public void get(View view){
        EditText editText=(EditText)findViewById(R.id.editText);
        String editStr=editText.getText().toString();
        editStr = editStr.replaceAll(" ", "%20");
        getRe("https://api.github.com/search/repositories?q="+editStr+"&sort=stars&order=desc");

    }
}