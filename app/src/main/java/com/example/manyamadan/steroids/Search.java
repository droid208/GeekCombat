package com.example.manyamadan.steroids;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by manyamadan on 17/07/16.
 */


    public class Search extends AppCompatActivity {

    EditText e;
    Button b;
    String data;
    private SharedPreferences mSharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search);

        e = (EditText) findViewById(R.id.edit);
        b = (Button) findViewById(R.id.go_dude);
        mSharedPreferences= PreferenceManager.getDefaultSharedPreferences(this);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                data = e.getText().toString();
                SharedPreferences.Editor editor=mSharedPreferences.edit();


                //editor.putString("address",item.getSubregion()+" "+item.getRegion());
                editor.putString("data",data);

                editor.apply();

                Intent i = new Intent(getApplicationContext(), Graphs.class);
                startActivity(i);


            }
        });


    }





}