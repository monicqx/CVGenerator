package com.example.monic.cvgenerator;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.monic.cvgenerator.Classes.Education;

public class EducationActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_education);


        //TODO: Education class
        //TODO: Education ArrayList (decide where to put it)
        //TODO: button for adding another School
        //TODO: IDEE: sa stergem ce e in EditTexturi si sa salvam Scoala curenta intr-un ArrayList, asa se pot adauga oricate scoli vrea useru
        // daca facem asa avem id-urile de la editTexturi. nu stiu cum sa facem sa apara alte EditTexturi sub cele pe care le avem acum
        // pt ArrayList cred ca cel mai bine cream o clasa pt Education
        // PROBLEMA: nu stiu cum le trimitem mai departe.. (tot prin intent, sa vedem cum)
        // alte idei?

        final Intent intent=getIntent();  //the intent that started this activity

        Button toWorkExperienceBtn = (Button)findViewById(R.id.E_toWorkExperienceBtn);
        toWorkExperienceBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),WorkExperienceActivity.class);
                //TODO: extract all data from EditTexts and pass it to the next Activity
                startActivity(intent);
            }
        });

        //Preluare Spinnere si populare cu ani
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(),
                R.layout.support_simple_spinner_dropdown_item, HomeActivity.years);
        final Spinner spinnerStart = (Spinner) findViewById(R.id.E_startYearSp);
        spinnerStart.setAdapter(adapter);
        final Spinner spinnerStop = (Spinner) findViewById(R.id.E_endYearSp);
        spinnerStop.setAdapter(adapter);

        //Golirea formularului si crearea unui obiect de tip Education cu datele completate
        Button addBtn = (Button)findViewById(R.id.E_addEducationBtn);
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText school = (EditText)findViewById(R.id.E_schoolET);
                EditText field = (EditText)findViewById(R.id.E_fieldOfStudyET);

                //TODO: Nu inteleg de ce nu merge
                if(school.getText().toString().isEmpty() || field.getText().toString().isEmpty()){
                    Toast.makeText(getApplicationContext(),"No empty fields allowed!",Toast.LENGTH_LONG);
                }

                Education education = new Education(school.getText().toString(),field.getText().toString(),spinnerStart.getSelectedItem().toString(),spinnerStop.getSelectedItem().toString());
                //TODO: de scos verificarea (eventual si tostringu)
                Toast.makeText(EducationActivity.this, education.toString(), Toast.LENGTH_LONG).show();

                //Golirea formularului
                school.getText().clear();
                field.getText().clear();
                spinnerStart.setSelection(0);
                spinnerStop.setSelection(0);
            }
        });

        Toast.makeText(getApplicationContext(),intent.getStringExtra("firstName")+" "+intent.getStringExtra("lastName")//
                +" "+intent.getStringExtra("telephone")+" "//
                +intent.getStringExtra("email")+" "+intent.getStringExtra("sex")//
                ,Toast.LENGTH_LONG ).show();//TODO: remove this
    }
}
