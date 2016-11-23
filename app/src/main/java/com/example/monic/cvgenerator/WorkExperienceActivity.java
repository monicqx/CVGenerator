package com.example.monic.cvgenerator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.monic.cvgenerator.Classes.WorkExperience;

public class WorkExperienceActivity extends AppCompatActivity {

    //TODO: class for work experience + array list
    //TODO: figure if there is a necessity to ever clear all the array lists
    //TODO: interface with button to add new that clears fields and adds to arraylist - for yearsStart spinner
    //TODO: button to next page and Intent

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_work_experience);

        //Populare spinnere
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(),
                R.layout.support_simple_spinner_dropdown_item, HomeActivity.yearsStart);
        final Spinner spinnerStart = (Spinner) findViewById(R.id.W_startYearSp);
        final Spinner spinnerStop = (Spinner) findViewById(R.id.W_endYearSp);
        spinnerStart.setAdapter(adapter);
        spinnerStop.setAdapter(adapter);

        final TextView label = (TextView) findViewById(R.id.W_endYearL);
        //Eliminarea optiunii de a alege anul daca este in prezent angajat
        final CheckBox checkBox = (CheckBox) findViewById(R.id.W_presentCB);
        checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                if(spinnerStop.getVisibility()==View.VISIBLE) {
                    spinnerStop.setVisibility(View.INVISIBLE);
                    label.setVisibility(View.INVISIBLE);
                }
                else{
                    spinnerStop.setVisibility(View.VISIBLE);
                    label.setVisibility(View.VISIBLE);
                }
            }
        });

        Button addWorkBtn = (Button)findViewById(R.id.W_addWorkBtn);
        addWorkBtn.setOnClickListener(new View.OnClickListener() {
            EditText positionET = (EditText)findViewById(R.id.W_positionET);
            EditText companyET = (EditText)findViewById(R.id.W_companyET);
            EditText descriptionET = (EditText)findViewById(R.id.W_descriptionET);
            @Override
            public void onClick(View v) {
                String position = positionET.getText().toString();
                String company = companyET.getText().toString();
                String startYear = spinnerStop.getSelectedItem().toString();
                String description = descriptionET.getText().toString();
                String endYear;
                if(spinnerStop.getVisibility()==View.INVISIBLE){
                    endYear = "present";
                }
                else{
                    endYear = spinnerStop.getSelectedItem().toString();
                }

                WorkExperience workExperience = new WorkExperience(position,company,startYear,endYear,description);
                CreateCVActivity.workExperienceArrayList.add(workExperience);

                //Golire formular
                positionET.getText().clear();
                companyET.getText().clear();
                descriptionET.getText().clear();
                spinnerStart.setSelection(0);
                spinnerStop.setSelection(0);
                checkBox.setChecked(false);
                spinnerStop.setVisibility(View.VISIBLE);
                label.setVisibility(View.VISIBLE);

            }
        });

    }
}
