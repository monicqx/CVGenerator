package com.example.monic.cvgenerator;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.Calendar;

public class HomeActivity extends AppCompatActivity {

    //Date pentru stocarea anilor
    private Calendar calendar = Calendar.getInstance();
    private int currentYear = calendar.get(Calendar.YEAR);
    public static ArrayList<String> yearsStart = new ArrayList<>();
    public static ArrayList<String> yearsEndEducation = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Button menuBtn=(Button)findViewById(R.id.H_menuBtn);
        menuBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),MenuActivity.class);
                startActivity(intent);
            }
        });
        computeYearsArray();

    }

    private void computeYearsArray() {
        for (int i = currentYear+10;i>currentYear;i--){
            yearsEndEducation.add(i+" ");
        }
        for(int i=currentYear;i>=1940;i--){
            yearsStart.add(i+"");
            yearsEndEducation.add(i+"");
        }
    }
}
