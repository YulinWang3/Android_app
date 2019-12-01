package com.example.a3010_project_attempt5;


import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity {
    Button click;
    Button choose;
    public static TextView data;
    public Intent intent;

    //                RequestQueue myque = Volley.newRequestQueue(MainActivity.this);
//              String url = "http://192.168.0.120:5000/";
//
//              StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
//                    new Response.Listener<String>() {
//                      @Override
//                    public void onResponse(String response) {
//
//                            data.setText(response);
//
//
//                          }
//                    }, new Response.ErrorListener() {
//              @Override
//            public void onErrorResponse(VolleyError error) {
//             data.setText("That does not work");

    //                  }
//            });

    //          myque.add(stringRequest);


    //FetchData process = new FetchData();
//process.execute();




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        click = (Button)findViewById(R.id.button);
        choose = findViewById(R.id.chooselocations);
        data = (TextView) findViewById(R.id.fetcheddata);

        intent = new Intent(this, Viewallvalues.class);


        click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                startActivity(intent);

            }
        });

        choose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fetchlocations process = new Fetchlocations();
                process.execute();
            }
        });


    }





}
