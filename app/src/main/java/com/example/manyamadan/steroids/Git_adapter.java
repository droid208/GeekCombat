package com.example.manyamadan.steroids;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

/**
 * Created by manyamadan on 17/07/16.
 */

public class Git_adapter extends BaseAdapter {
    private Activity activity;
    private LayoutInflater inflater;
    private List<GitModel> orderList;
    TextView id,skills,mail;
    Context context;

    public Git_adapter(Context context, List<GitModel> orderList) {
        this.context = context;
        this.orderList = orderList;

    }

    @Override
    public int getCount() {
        return orderList.size();
    }

    @Override
    public Object getItem(int location) {
        return orderList.get(location);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (inflater == null)
            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (convertView == null)
            convertView = inflater.inflate(R.layout.list_item, null);

        id=(TextView) convertView.findViewById(R.id.git_id);


        final GitModel m = orderList.get(position);
        String url_str =m.getUrl();
       // getRe(url_str);

       // mail.setText(m.getMail());
        id.setText(m.getUser_id());


        return convertView;
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

            }
        });
        Volley.newRequestQueue(context).add(request);
    }

    private void pars(String s){
        JSONObject object=null;
        try{
            object=new JSONObject(s);
            JSONArray array=object.getJSONArray("items");
            for(int i=0;i<array.length();i++){
                JSONObject object1=array.getJSONObject(i);
                GitModel gitModel=new GitModel();
                gitModel.setUrl(object1.getString("url"));
                gitModel.setUser_id(object1.getString("login"));
            }
        }
        catch (JSONException e){

        }
    }

}

