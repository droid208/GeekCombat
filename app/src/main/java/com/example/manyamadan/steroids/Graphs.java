package com.example.manyamadan.steroids;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by manyamadan on 17/07/16.
 */
public class Graphs  extends AppCompatActivity {

String data;
    int maxPos;
    float max;
    String maxCity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.graphs);

        final SharedPreferences mSharedPreference= PreferenceManager.getDefaultSharedPreferences(getBaseContext());
       // SharedPreferences.Editor editor=mSharedPreference.edit();
         data =mSharedPreference.getString("data", "lol");
        Toast.makeText(getApplicationContext(),data,Toast.LENGTH_LONG).show();
        requestToServer("http://geekst.herokuapp.com/data/");

    }
    ArrayList<String> xstr;
    public static String coordinates="[\"28.758700, 77.073984,20km\",\"28.663549, 77.200326,20km\",\"28.615338, 77.003946,20km\",\"28.541773, 77.190713,20km\"]";
    ArrayList<BarEntry> valueSet1;
    public void requestToServer(String urlstr)       // sending request to the server
    {       final int count =0;






        StringRequest myreq = new StringRequest(com.android.volley.Request.Method.POST, urlstr,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        Log.d("mytag",response);
                       xstr=new ArrayList<>();
                       // xstr=new ArrayList<>();
                        max=0;maxPos=0;
                        JSONArray jsonArray=null;
                        try {

                            valueSet1 = new ArrayList<>();
                            jsonArray = new JSONArray(response);

                            for (int i = 0; i < jsonArray.length(); i++)
                            {
                                JSONObject obj = jsonArray.getJSONObject(i);

                                float a = Integer.parseInt(obj.getString("count"));
                                if(max<a)
                                {   max=a;
                                    maxPos=i;
                                }
                                BarEntry v1e1 = new BarEntry(a, i);
                                valueSet1.add(v1e1);
                                xstr.add(obj.getString("region"));


//                                medList.add(home);
//
//                                if(Substitutes.adapter!=null)
//                                    Substitutes.adapter.notifyDataSetChanged();
//
//                                if( CosmeticsFragment.adapter != null)
//                                    CosmeticsFragment. adapter.notifyDataSetChanged();


                            }
                            BarDataSet barDataSet1 = new BarDataSet(valueSet1, "values");
                            barDataSet1.setColor(Color.rgb(255,0,255));




                            BarChart chart = (BarChart) findViewById(R.id.chart);

                            BarData data = new BarData(xstr, barDataSet1);
                          //  Log.d("mytag",xstr.toString());
                            chart.setData(data);
                            chart.setDescription("My Chart");
                            chart.animateXY(2000, 2000);
                            chart.invalidate();
                            maxCity=xstr.get(maxPos);
                            subArea();
                        }
                        catch (JSONException e){
                            Log.d("mytag", e.toString() + "   asddsaasd");
                            if(jsonArray.length()==0)
                            {   Log.d("mytag", "asddsaasd");
//                                CosmeticsFragment.showNoTextShow();
                            }
                        }
                        catch (Exception e)
                        {
                            e.printStackTrace();
                        }




                    }
                }
                ,
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {




                        Toast.makeText(Graphs.this, "Please Check Your network Connection"+error.toString(), Toast.LENGTH_LONG).show();
                        //AppController.sendExceptions(error,context);

                    }
                }
        ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String,String> params=new HashMap<String,String>();

                params.put("term",data);
                //params.put("user_id",preferences.getString("userid",null));

                return params;
            }
        };

        Volley.newRequestQueue(Graphs.this).add(myreq);
    }



    String subString,subCor;
    int subPos,subMax;
    private void subArea(){
        StringRequest request=new StringRequest(Request.Method.POST, "http://geekst.herokuapp.com/data/sub/",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
                        Log.d("mytag",s);
                        ArrayList<String> subRegion=new ArrayList<>();
                        subRegion.add("North "+maxCity);
                        subRegion.add("East "+maxCity);
                        subRegion.add("West"+maxCity);
                        subRegion.add("South "+maxCity);

                        ArrayList<BarEntry> value=new ArrayList<>();
                        JSONArray array=null;
                        try{
                            subMax=0;
                            subPos=0;
                            array=new JSONArray(s);
                            for(int i=0;i<array.length();i++){
                                JSONObject object=array.getJSONObject(i);
                                BarEntry v1e1 = new BarEntry(object.getInt("c"), i);

                                value.add(v1e1);
                                if(object.getInt("c")>subMax){
                                    subMax=object.getInt("c");
                                    subPos=i;
                                }
                            }


                            BarDataSet barDataSet1 = new BarDataSet(value, "values");
                            barDataSet1.setColor(Color.rgb(30, 60, 60));

                            BarChart chart = (BarChart) findViewById(R.id.chartSub);
                            chart.setVisibility(View.VISIBLE);                                                                                                                                                 subCor="28.758700, 77.073984,20km";

                            BarData data = new BarData(subRegion, barDataSet1);
                            //  Log.d("mytag",xstr.toString());
                            chart.setData(data);
                            chart.setDescription("My Chart");
                            chart.animateXY(2000, 2000);
                            chart.invalidate();

                            subString=subRegion.get(subPos);
                            sentiArea();
                        }catch (JSONException e){

                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params=new HashMap<String,String>();

                params.put("term",data);
                //params.put("user_id",preferences.getString("userid",null));
                params.put("city",coordinates);
                return params;
            }
        };
        Volley.newRequestQueue(Graphs.this).add(request);

    }


    private void sentiArea(){
        StringRequest request=new StringRequest(Request.Method.POST, "http://geekst.herokuapp.com/data/senti/",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
                        Log.d("mytag",s);
                        ArrayList<String> sentiRegion=new ArrayList<>();
                        sentiRegion.add("Negative");
                        sentiRegion.add("Neutral");
                        sentiRegion.add("Positive");

                        ArrayList<Entry> value=new ArrayList<>();
                        JSONArray array=null;
                        try{
                            subMax=0;
                            subPos=0;
                            JSONObject object=new JSONObject(s);
                            float neg=object.getInt("neg");
                            float neu=object.getInt("neu");
                            float pos=object.getInt("pos");

                            PieChart pieChart = (PieChart) findViewById(R.id.pieSenti);
                            pieChart.setVisibility(View.VISIBLE);
                            ArrayList<Entry> entries = new ArrayList<>();
                            entries.add(new Entry(neg, 0));
                            entries.add(new Entry(neu, 1));
                            entries.add(new Entry(pos, 2));

                            PieDataSet dataset = new PieDataSet(entries, "# of Calls");

                            PieData data = new PieData(sentiRegion, dataset);
                            dataset.setColors(ColorTemplate.COLORFUL_COLORS); //
                            pieChart.setDescription("Description");
                            pieChart.setData(data);

                            pieChart.animateY(5000);
                        }catch (JSONException e){

                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params=new HashMap<String,String>();

                params.put("term",data);
                //params.put("user_id",preferences.getString("userid",null));
                params.put("city",subCor);
                return params;
            }
        };
        Volley.newRequestQueue(Graphs.this).add(request);

    }

}