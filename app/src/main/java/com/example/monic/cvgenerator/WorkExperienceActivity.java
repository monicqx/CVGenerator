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

    //TODO: figure if there is a necessity to ever clear all the array lists
    //TODO: button to next page and Intent

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_work_experience);

        final Spinner startYearSpinner = (Spinner) findViewById(R.id.W_startYearSp);
        final Spinner endYearSpinner = (Spinner) findViewById(R.id.W_endYearSp);
        final TextView labelEndYear = (TextView) findViewById(R.id.W_endYearL);
        final CheckBox presentCheckBox = (CheckBox) findViewById(R.id.W_presentCB);
        final Button addWorkExperienceBtn = (Button)findViewById(R.id.W_addWorkExperienceBtn);
        final EditText positionET = (EditText)findViewById(R.id.W_positionET);
        final EditText companyET = (EditText)findViewById(R.id.W_companyET);
        final EditText descriptionET = (EditText)findViewById(R.id.W_descriptionET);

        populateSpinners(startYearSpinner,endYearSpinner);

        //Eliminarea optiunii de a alege anul daca este in prezent angajat
        presentCheckBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                if(endYearSpinner.getVisibility()==View.VISIBLE) {
                    endYearSpinner.setVisibility(View.INVISIBLE);
                    labelEndYear.setVisibility(View.INVISIBLE);
                }
                else{
                    endYearSpinner.setVisibility(View.VISIBLE);
                    labelEndYear.setVisibility(View.VISIBLE);
                }
            }
        });


        addWorkExperienceBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //TODO: check is the EditTexts are empty before creating the objects

                String position = positionET.getText().toString();
                String company = companyET.getText().toString();
                String startYear = endYearSpinner.getSelectedItem().toString();
                String description = descriptionET.getText().toString();
                String endYear;
                if(endYearSpinner.getVisibility()==View.INVISIBLE){
                    endYear = "present";
                }
                else{
                    endYear = endYearSpinner.getSelectedItem().toString();
                }

                //TODO: create method that checks if controls are empty (copy from EducationActivity)
                WorkExperience workExperience = new WorkExperience(position,company,startYear,endYear,description);
                CreateCVActivity.workExperienceArrayList.add(workExperience);

                emptyControls(positionET,companyET,descriptionET,startYearSpinner,endYearSpinner,presentCheckBox,labelEndYear);

            }
        });

    }


    private void emptyControls(EditText positionET, EditText companyET, EditText descriptionET, Spinner startYearSpinner, Spinner endYearSpinner, CheckBox presentCheckBox, TextView labelEndYear) {
        positionET.getText().clear();
        companyET.getText().clear();
        descriptionET.getText().clear();
        startYearSpinner.setSelection(0);
        endYearSpinner.setSelection(0);
        presentCheckBox.setChecked(false);
        endYearSpinner.setVisibility(View.VISIBLE);
        labelEndYear.setVisibility(View.VISIBLE);
    }

    /**
     * @param startYearSpinner
     * @param endYearSpinner
     *
     * Populates spinners with the ArrayList containing the years.
     */
    private void populateSpinners(Spinner startYearSpinner, Spinner endYearSpinner) {
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(),
                R.layout.support_simple_spinner_dropdown_item, HomeActivity.yearsStart);

        startYearSpinner.setAdapter(adapter);
        endYearSpinner.setAdapter(adapter);
    }
}
