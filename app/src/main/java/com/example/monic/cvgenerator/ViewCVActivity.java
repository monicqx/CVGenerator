package com.example.monic.cvgenerator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class ViewCVActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_cv);

        if(CreateCVActivity.profile!=null){
            TextView cvTV=(TextView)findViewById(R.id.VCV_cv);
            cvTV.setText(CreateCVActivity.profile.toString());
        }

    }
}
