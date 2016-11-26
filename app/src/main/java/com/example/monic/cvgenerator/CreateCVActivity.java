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

import com.example.monic.cvgenerator.Classes.Education;
import com.example.monic.cvgenerator.Classes.Language;
import com.example.monic.cvgenerator.Classes.SocialNetworksFragment;
import com.example.monic.cvgenerator.Classes.WorkExperience;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class CreateCVActivity extends AppCompatActivity implements SocialNetworksFragment.OnFragmentInteractionListener {
    private EditText birthdayET;

    @Override
    public void onFragmentInteraction(Uri uri) {
    }
    private DatePickerDialog datePickerDialog;
    private SimpleDateFormat dateFormatter;

    private String firstName = null;
    private String lastName = null;
    private String telephone = null;
    private String email = null;
    private String sex = null;
    private String birthday =null;

    //Colectii ce retin diverse informatii
    public static Map<String, String> socialNetworksMap = new HashMap<>();
    public static ArrayList<Education> educationArrayList = new ArrayList<Education>();
    public static ArrayList<WorkExperience> workExperienceArrayList = new ArrayList<WorkExperience>();
    public static ArrayList<Language> languagesArrayList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_cv);

        final RadioGroup sexRadioGroup = (RadioGroup) findViewById(R.id.C_sexRadioGr);
        final Button addSocialNetworkBtn = (Button) findViewById(R.id.C_addSocialNetworksBtn);
        final Button toEducationBtn = (Button) findViewById(R.id.C_toEducationBtn);

        addSocialNetworkBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction t = getFragmentManager().beginTransaction();
                SocialNetworksFragment fragment = SocialNetworksFragment.newInstance();
                fragment.show(t, "dialog");
            }
        });

        toEducationBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firstName = ((EditText) findViewById(R.id.C_firstNameET)).getText().toString();
                lastName = ((EditText) findViewById(R.id.C_lastNameET)).getText().toString();
                telephone = ((EditText) findViewById(R.id.C_telephoneET)).getText().toString();
                email = ((EditText) findViewById(R.id.C_emailET)).getText().toString();
                if (sexRadioGroup.getCheckedRadioButtonId() != -1) //if one of the radio buttons is checked
                    sex = ((RadioButton) findViewById(sexRadioGroup.getCheckedRadioButtonId())).getText().toString();

                //TODO: check if e-mail; if not ok then make text red
                //TODO: get data from map and add it to intent  - Bianca

                //Toast.makeText(getApplicationContext(),socialNetworksMap.get("LinkedIn")+" "+socialNetworksMap.get("Skype"),Toast.LENGTH_LONG).show();

                Intent intent = new Intent(getApplicationContext(), EducationActivity.class);
                intent.putExtra("firstName", firstName);
                intent.putExtra("lastName", lastName);
                intent.putExtra("telephone", telephone);
                intent.putExtra("email", email);
                intent.putExtra("sex", sex);
                intent.putExtra("birthday",birthday);
                startActivity(intent);
            }
        });

        //TODO: monica: eu zic sa facem doar un EditText cu data nasterii si sa o bage useru. Nu-mi place deloc cum arata
        // ce e mai jos, zic ca ne complicam degeaba

        dateFormatter = new SimpleDateFormat("dd-MM-yyyy", Locale.US);
        findViewsById();
        setDateTimeField();
    }

    /*public void onFragmentInteraction(Uri uri) {
        String socialNetwork=uri.toString();
        Toast.makeText(getApplicationContext(),socialNetwork,Toast.LENGTH_LONG).show();
    }*/

    private void findViewsById() {
        birthdayET = (EditText) findViewById(R.id.C_chooseBirthdayET);
        birthdayET.setInputType(InputType.TYPE_NULL);
        birthdayET.requestFocus();
    }

    private String setDateTimeField() {
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
                birthdayET.setText(dateFormatter.format(newDate.getTime()));
                birthday =dateFormatter.format(newDate.getTime()).toString();
            }
        }, newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));
        return birthday;
    }


}