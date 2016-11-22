package com.example.monic.cvgenerator;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class EducationActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_education);


        //TODO: Education class
        //TODO: Education ArrayList (decide where to put it)
        //TODO: years for spinner (1940-current year)
        //TODO: button for adding another School
        //TODO: IDEE: sa stergem ce e in EditTexturi si sa salvam Scoala curenta intr-un ArrayList, asa se pot adauga oricate scoli vrea useru
        // daca facem asa avem id-urile de la editTexturi. nu stiu cum sa facem sa apara alte EditTexturi sub cele pe care le avem acum
        // pt ArrayList cred ca cel mai bine cream o clasa pt Education
        // PROBLEMA: nu stiu cum le trimitem mai departe.. (tot prin intent, sa vedem cum)
        // alte idei?

        Intent intent=getIntent();  //the intent that started this activity

        Button toWorkExperienceBtn = (Button)findViewById(R.id.E_toWorkExperienceBtn);
        toWorkExperienceBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),WorkExperienceActivity.class);
                //TODO: extract all data from EditTexts and pass it to the next Activity
                startActivity(intent);
            }
        });


        Toast.makeText(getApplicationContext(),intent.getStringExtra("firstName")+" "+intent.getStringExtra("lastName")//
                +" "+intent.getStringExtra("telephone")+" "//
                +intent.getStringExtra("email")+" "+intent.getStringExtra("sex")//
                ,Toast.LENGTH_LONG ).show();//TODO: remove this
    }
}
