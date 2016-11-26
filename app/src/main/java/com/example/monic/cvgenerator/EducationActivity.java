package com.example.monic.cvgenerator;

import android.content.Intent;
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

        //TODO: bianca: done dar trebuie sa verificam daca e totul ok
        //TODO: button for adding another School
        //TODO: IDEE: sa stergem ce e in EditTexturi si sa salvam Scoala curenta intr-un ArrayList, asa se pot adauga oricate scoli vrea useru
        // daca facem asa avem id-urile de la editTexturi. nu stiu cum sa facem sa apara alte EditTexturi sub cele pe care le avem acum
        // pt ArrayList cred ca cel mai bine cream o clasa pt Education
        // PROBLEMA: nu stiu cum le trimitem mai departe.. (tot prin intent, sa vedem cum)
        // alte idei?

        final Intent intent=getIntent();  //the intent that started this activity
        final Spinner startYearSpinner = (Spinner) findViewById(R.id.E_startYearSp);
        final Spinner endYearSpinner = (Spinner) findViewById(R.id.E_endYearSp);
        final Button addEducationBtn = (Button)findViewById(R.id.E_addEducationBtn);
        final EditText schoolET = (EditText)findViewById(R.id.E_schoolET);
        final EditText fieldET = (EditText)findViewById(R.id.E_fieldOfStudyET);

        populateSpinners(startYearSpinner,endYearSpinner);

        Button toWorkExperienceBtn = (Button)findViewById(R.id.E_toWorkExperienceBtn);
        toWorkExperienceBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(),WorkExperienceActivity.class);

                if(noEducationAdded() && controlsAreEmpty(schoolET,fieldET)){
                    Toast.makeText(EducationActivity.this, "Please add at least one Education entry.", Toast.LENGTH_LONG).show();
                }else if(!controlsAreEmpty(schoolET,fieldET)){
                    Education education = new Education(schoolET.getText().toString(), fieldET.getText().toString(), startYearSpinner.getSelectedItem().toString(), endYearSpinner.getSelectedItem().toString());
                    CreateCVActivity.educationArrayList.add(education);
                    clearControls(schoolET,fieldET,startYearSpinner,endYearSpinner);
                    startActivity(intent);
                }
                else{
                    startActivity(intent);
                }
                //TODO: extract all data from EditTexts and pass it to the next Activity
            }
        });

        //Golirea formularului si crearea unui obiect de tip Education cu datele completate
        addEducationBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //TODO: call method when clicking on "toWorkExperienceBtn" button as well
                if(controlsAreEmpty(schoolET,fieldET)){
                    Toast.makeText(getApplicationContext(),"No empty fields allowed!",Toast.LENGTH_LONG).show();
                }else {
                    Education education = new Education(schoolET.getText().toString(), fieldET.getText().toString(), startYearSpinner.getSelectedItem().toString(), endYearSpinner.getSelectedItem().toString());
                    CreateCVActivity.educationArrayList.add(education);
                }
                clearControls(schoolET,fieldET,startYearSpinner,endYearSpinner);
            }
        });

        Toast.makeText(getApplicationContext(),intent.getStringExtra("firstName")+" "+intent.getStringExtra("lastName")//
                +" "+intent.getStringExtra("telephone")+" "//
                +intent.getStringExtra("email")+" "+intent.getStringExtra("sex")//
                ,Toast.LENGTH_LONG ).show();//TODO: remove this
    }

    private boolean noEducationAdded() {
        if(CreateCVActivity.educationArrayList.isEmpty())
            return true;
        return false;
    }

    private boolean controlsAreEmpty(EditText schoolET, EditText fieldET) {

        if(schoolET.getText().toString().isEmpty())
            return true;
        if(fieldET.getText().toString().isEmpty())
            return true;
        return false;
    }

    private void clearControls(EditText schoolET, EditText fieldET, Spinner startYearSpinner, Spinner endYearSpinner) {
        schoolET.getText().clear();
        fieldET.getText().clear();
        startYearSpinner.setSelection(0);
        endYearSpinner.setSelection(0);
    }

    /**
     * @param startYearSpinner
     * @param endYearSpinner
     * Populates spinners with the ArrayList containing the years.
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
