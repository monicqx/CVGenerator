package com.example.monic.cvgenerator;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MenuActivity extends AppCompatActivity {

    private Button createCVBtn = null;
    private Button viewCVBtn = null;
    private Button cvTipsBtn=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        findViewsById();

        createCVBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), CreateCVActivity.class);
                startActivity(intent);
            }
        });

        viewCVBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ViewCVActivity.class);
                startActivity(intent);
            }
        });

        cvTipsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),TipsActivity.class);
                startActivity(intent);
            }
        });
    }

    private void findViewsById() {
        createCVBtn = (Button) findViewById(R.id.M_createCVBtn);
        viewCVBtn = (Button) findViewById(R.id.M_viewCVBtn);
        cvTipsBtn=(Button)findViewById(R.id.M_tipsCVBtn);
    }
}
