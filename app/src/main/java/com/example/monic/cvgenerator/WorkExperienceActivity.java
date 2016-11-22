package com.example.monic.cvgenerator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Calendar;

public class WorkExperienceActivity extends AppCompatActivity {

    //TODO: class for work experience + array list
    //TODO: figure if there is a necessity to ever clear all the array lists
    //TODO: interface with button to add new that clears fields and adds to arraylist - for years spinner
    //TODO: button to next page and Intent

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_work_experience);

        Spinner sp = (Spinner)findViewById(R.id.W_startYearSp);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(),
                R.layout.support_simple_spinner_dropdown_item, HomeActivity.years);
        Spinner spinner = (Spinner) findViewById(R.id.W_startYearSp);
        spinner.setAdapter(adapter);
    }
}
