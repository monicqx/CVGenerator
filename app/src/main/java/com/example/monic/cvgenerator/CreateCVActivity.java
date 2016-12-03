package com.example.monic.cvgenerator;

import android.app.DatePickerDialog;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.monic.cvgenerator.Classes.Certificate;
import com.example.monic.cvgenerator.Classes.Education;
import com.example.monic.cvgenerator.Classes.Profile;
import com.example.monic.cvgenerator.Classes.Skill;
import com.example.monic.cvgenerator.Classes.SocialNetworksFragment;
import com.example.monic.cvgenerator.Classes.WorkExperience;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class CreateCVActivity extends AppCompatActivity implements SocialNetworksFragment.OnFragmentInteractionListener {

    private EditText firstNameET = null;
    private EditText lastNameET = null;
    private EditText telephoneET = null;
    private EditText emailET = null;
    private RadioGroup sexRadioGroup = null;
    private EditText birthdayET;
    private Button addSocialNetworkBtn=null;
    private Button toEducationBtn=null;
    private DatePickerDialog datePickerDialog;
    private SimpleDateFormat dateFormatter;
    private Calendar birthday=null;

    public static Profile profile=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_cv);

        dateFormatter = new SimpleDateFormat("dd-MM-yyyy", Locale.US);

        findViewsById();
        setDateTimeField();

        profile=new Profile();

        addSocialNetworkBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAddSocialNetworkDialog();
            }
        });

        toEducationBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar c=Calendar.getInstance();

                if(controlsAreEmpty()){
                    Toast.makeText(getApplicationContext(),"No empty fields allowed!",Toast.LENGTH_LONG).show();
                }
                else {
                    if(!birthday.before(c)){    //if brithday is not set before today
                        Toast.makeText(getApplicationContext(),"Please enter a valid birthday.",Toast.LENGTH_LONG).show();
                    }else {
                        String sex = ((RadioButton) findViewById(sexRadioGroup.getCheckedRadioButtonId())).getText().toString();
                        profile.setFirstName(firstNameET.getText().toString());
                        profile.setLastName(lastNameET.getText().toString());
                        profile.setTelephone(telephoneET.getText().toString());
                        profile.setEmail(emailET.getText().toString());
                        profile.setSex(sex);
                        profile.setBirthday(dateFormatter.format(birthday.getTime()).toString());

                        Intent intent = new Intent(getApplicationContext(), EducationActivity.class);
                        startActivity(intent);
                    }
                }
            }
        });
    }

    private void showAddSocialNetworkDialog() {
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        SocialNetworksFragment fragment = SocialNetworksFragment.newInstance();
        fragment.show(fragmentTransaction, "dialog");
    }

    private boolean controlsAreEmpty() {
        if(firstNameET.getText().toString().isEmpty())
            return true;
        if(lastNameET.getText().toString().isEmpty())
            return true;
        if(telephoneET.getText().toString().isEmpty())
            return true;
        if(emailET.getText().toString().isEmpty())
            return true;
        if(birthdayET.getText().toString().isEmpty())
            return true;
        if(sexRadioGroup.getCheckedRadioButtonId() == -1)
            return true;
        return false;
    }

    @Override
    public void onFragmentInteraction(Uri uri) {
    }

    private void findViewsById() {
        birthdayET = (EditText) findViewById(R.id.C_chooseBirthdayET);
        birthdayET.setInputType(InputType.TYPE_NULL);
        birthdayET.requestFocus();

        firstNameET = ((EditText) findViewById(R.id.C_firstNameET));
        lastNameET = ((EditText) findViewById(R.id.C_lastNameET));
        telephoneET = ((EditText) findViewById(R.id.C_telephoneET));
        emailET = ((EditText) findViewById(R.id.C_emailET));
        sexRadioGroup=  (RadioGroup)findViewById(R.id.C_sexRadioGr);
        addSocialNetworkBtn = (Button) findViewById(R.id.C_addSocialNetworksBtn);
        toEducationBtn = (Button) findViewById(R.id.C_toEducationBtn);

    }

    private void setDateTimeField() {
        birthdayET.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v == birthdayET) {
                    datePickerDialog.show();
                }
            }
        });
        Calendar newCalendar = Calendar.getInstance();
        datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                birthday=newDate;
                birthdayET.setText(dateFormatter.format(newDate.getTime()));
            }
        }, newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));
    }
}