package com.example.a3010_project_attempt5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class Viewallvalues extends AppCompatActivity {
    public static TextView allvalues;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewallvalues);


        allvalues = findViewById(R.id.allvaluesview);


        FetchData process = new FetchData();
        process.execute();

    }
}
