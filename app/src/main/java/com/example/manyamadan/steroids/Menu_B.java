package com.example.manyamadan.steroids;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Menu_B extends AppCompatActivity {

    Button graph,vcs,recruit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu__b);

        graph = (Button) findViewById(R.id.analytics);
        vcs = (Button) findViewById(R.id.vc);
        recruit = (Button) findViewById(R.id.recruit);
        graph.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), Search.class);
                startActivity(i);

            }
        });

        recruit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), Git.class);
                startActivity(i);
            }
        });





    }
}
