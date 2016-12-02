package com.example.monic.cvgenerator;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.monic.cvgenerator.Classes.Education;

public class EducationActivity extends AppCompatActivity {
    private Spinner startYearSpinner = null;
    private Spinner endYearSpinner = null;
    private EditText schoolET = null;
    private EditText fieldET = null;
    private Button addEducationBtn = null;
    private Button toWorkExperienceBtn=null;
    private RadioGroup typeOfStudyRadioGroup=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_education);

        final Intent previousIntent= getIntent();

        findViewsById();

        populateSpinners(startYearSpinner, endYearSpinner);

        //Golirea formularului si crearea unui obiect de tip Education cu datele completate
        addEducationBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (controlsAreEmpty(schoolET, fieldET)) {
                    Toast.makeText(getApplicationContext(), "No empty fields allowed!", Toast.LENGTH_LONG).show();
                } else {
                    String type=((RadioButton) findViewById(typeOfStudyRadioGroup.getCheckedRadioButtonId())).getText().toString();
                    Education education = new Education(schoolET.getText().toString(), fieldET.getText().toString(), startYearSpinner.getSelectedItem().toString(), endYearSpinner.getSelectedItem().toString(),type);
                    CreateCVActivity.educationArrayList.add(education);
                    clearControls(schoolET, fieldET, startYearSpinner, endYearSpinner,typeOfStudyRadioGroup);
                }

            }
        });

        toWorkExperienceBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), WorkExperienceActivity.class);

                if (noEducationAdded() && controlsAreEmpty(schoolET, fieldET)) {
                    Toast.makeText(EducationActivity.this, "Please add at least one Education entry.", Toast.LENGTH_LONG).show();
                } else if (!controlsAreEmpty(schoolET, fieldET)) {
                    String type=((RadioButton) findViewById(typeOfStudyRadioGroup.getCheckedRadioButtonId())).getText().toString();
                    Education education = new Education(schoolET.getText().toString(), fieldET.getText().toString(), startYearSpinner.getSelectedItem().toString(), endYearSpinner.getSelectedItem().toString(),type);
                    CreateCVActivity.educationArrayList.add(education);
                    clearControls(schoolET, fieldET, startYearSpinner, endYearSpinner, typeOfStudyRadioGroup);
                    startActivity(intent);
                } else {
                    startActivity(intent);
                }
            }
        });

        Toast.makeText(getApplicationContext(), previousIntent.getStringExtra("firstName") + " " + previousIntent.getStringExtra("lastName")//
                        + " " + previousIntent.getStringExtra("telephone") + " "//
                        + previousIntent.getStringExtra("email") + " " + previousIntent.getStringExtra("sex")//
                , Toast.LENGTH_LONG).show();//TODO: remove this
    }


    private boolean noEducationAdded() {
        if (CreateCVActivity.educationArrayList.isEmpty())
            return true;
        return false;
    }

    private void findViewsById() {
        startYearSpinner = (Spinner) findViewById(R.id.E_startYearSp);
        endYearSpinner = (Spinner) findViewById(R.id.E_endYearSp);
        addEducationBtn = (Button) findViewById(R.id.E_addEducationBtn);
        schoolET = (EditText) findViewById(R.id.E_schoolET);
        fieldET = (EditText) findViewById(R.id.E_fieldOfStudyET);
        toWorkExperienceBtn = (Button) findViewById(R.id.E_toWorkExperienceBtn);
        typeOfStudyRadioGroup=(RadioGroup) findViewById(R.id.E_typeOfStudyRadioGr);
    }

    private boolean controlsAreEmpty(EditText schoolET, EditText fieldET) {

        if (schoolET.getText().toString().isEmpty())
            return true;
        if (fieldET.getText().toString().isEmpty())
            return true;
        if(typeOfStudyRadioGroup.getCheckedRadioButtonId()==-1)
            return true;
        return false;
    }

    private void clearControls(EditText schoolET, EditText fieldET, Spinner startYearSpinner, Spinner endYearSpinner, RadioGroup typeOfStudyRadioGroup) {
        schoolET.getText().clear();
        fieldET.getText().clear();
        startYearSpinner.setSelection(0);
        endYearSpinner.setSelection(0);
        typeOfStudyRadioGroup.clearCheck();
    }

    /**
     * @param startYearSpinner
     * @param endYearSpinner   Populates spinners with the ArrayList containing the years.
     */
    private void populateSpinners(Spinner startYearSpinner, Spinner endYearSpinner) {
        ArrayAdapter<String> adapterStart = new ArrayAdapter<String>(getApplicationContext(),
                R.layout.support_simple_spinner_dropdown_item, HomeActivity.yearsStart);
        ArrayAdapter<String> adapterEnd = new ArrayAdapter<String>(getApplicationContext(),
                R.layout.support_simple_spinner_dropdown_item, HomeActivity.yearsEndEducation);
        startYearSpinner.setAdapter(adapterStart);
        endYearSpinner.setAdapter(adapterEnd);
    }
}
