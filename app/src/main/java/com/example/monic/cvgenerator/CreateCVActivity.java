package com.example.monic.cvgenerator;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class CreateCVActivity extends AppCompatActivity {
    private EditText birthday;
    private DatePickerDialog datePickerDialog;
    private SimpleDateFormat dateFormatter;

    private Button toEducationBtn=null;
    private String firstName=null;
    private String lastName=null;
    private String telephone=null;
    private String email=null;
    private String socialNetwork=null;
    private String sex=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_cv);

        //TODO: after selecting from spinner: either open a popup window (using fragment), or insert an EditText

        toEducationBtn=(Button)findViewById(R.id.C_toEducationBtn);
        toEducationBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firstName=((EditText)findViewById(R.id.C_firstNameET)).getText().toString();
                lastName=((EditText)findViewById(R.id.C_lastNameET)).getText().toString();
                telephone=((EditText)findViewById(R.id.C_telephoneET)).getText().toString();
                email=((EditText)findViewById(R.id.C_emailET)).getText().toString();
                socialNetwork=((Spinner)findViewById(R.id.C_socialNetworkSp)).getSelectedItem().toString();

                RadioGroup sexRadioGroup = (RadioGroup)findViewById(R.id.C_sexRadioGr);
                if(sexRadioGroup.getCheckedRadioButtonId()!=-1) //if one of the radio buttons is checked
                    sex = ((RadioButton)findViewById(sexRadioGroup.getCheckedRadioButtonId())).getText().toString();

                //TODO: get birthday text
                //TODO: check if e-mail; if not ok then make text red

                Intent intent=new Intent(getApplicationContext(),EducationActivity.class);
                intent.putExtra("firstName",firstName);
                intent.putExtra("lastName",lastName);
                intent.putExtra("telephone",telephone);
                intent.putExtra("email",email);
                intent.putExtra("socialNetwork",socialNetwork);
                intent.putExtra("sex",sex);
                startActivity(intent);
            }
        });

        //TODO: monica: eu zic sa facem doar un EditText cu data nasterii si sa o bage useru. Nu-mi place deloc cum arata
        // ce e mai jos, zic ca ne complicam degeaba

        dateFormatter = new SimpleDateFormat("dd-MM-yyyy", Locale.US);
        findViewsById();
        setDateTimeField();

    }

    private void findViewsById() {
        birthday = (EditText) findViewById(R.id.C_chooseBirthdayET);
        birthday.setInputType(InputType.TYPE_NULL);
        birthday.requestFocus();
    }

    private void setDateTimeField() {
        birthday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(v == birthday) {
                    datePickerDialog.show();
                }
            }
        });

        Calendar newCalendar = Calendar.getInstance();
        datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                birthday.setText(dateFormatter.format(newDate.getTime()));
            }
        },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));

    }



}