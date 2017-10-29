package com.example.manyamadan.steroids;

import android.content.Context;
import android.util.Log;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

/**
 * Created by dilpreet on 17/7/16.
 */
public class GitRequest {
    Context context;

    public GitRequest(Context context) {
        super();
        this.context=context;
    }
    public void getRe(String url, final List<GitModel> modelList, final Git_adapter adapter){
        StringRequest request=new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                pars(s,modelList,adapter);
                Log.d("mytag",s);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Log.d("mytag",volleyError.toString());
            }
        });
        Volley.newRequestQueue(context).add(request);
    }

    private void pars(String s,List<GitModel> modelList,Git_adapter adapter){
        JSONObject object=null;
        try{
                object=new JSONObject(s);
            JSONArray array=object.getJSONArray("items");
            for(int i=0;i<array.length();i++){
                JSONObject object1=array.getJSONObject(i);
                GitModel gitModel=new GitModel();
                gitModel.setUrl(object1.getString("url"));
                gitModel.setUser_id(object1.getString("login"));
                modelList.add(gitModel);
            }
            adapter.notifyDataSetChanged();
            }
        catch (JSONException e){

        }
    }
}
