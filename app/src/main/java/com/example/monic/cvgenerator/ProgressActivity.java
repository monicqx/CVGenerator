package com.example.monic.cvgenerator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.LinearLayout;

import com.example.monic.cvgenerator.View.ProgressView;

public class ProgressActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progress);

        LinearLayout linearLayout = (LinearLayout)findViewById(R.id.pieChart);
        ProgressView view = new ProgressView(this);
        linearLayout.addView(view,1000,3000);
    }
}
