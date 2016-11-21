package com.example.monic.cvgenerator;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class EducationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_education);

        //TODO: button for adding another School
        //TODO: IDEE: sa stergem ce e in EditTexturi si sa salvam Scoala curenta intr-un ArrayList, asa se pot adauga oricate scoli vrea useru
        // daca facem asa avem id-urile de la editTexturi. nu stiu cum sa facem sa apara alte EditTexturi sub cele pe care le avem acum
        // pt ArrayList cred ca cel mai bine cream o clasa pt Education
        // PROBLEMA: nu stiu cum le trimitem mai departe.. (tot prin intent, sa vedem cum)
        // alte idei?


        Intent intent=getIntent();
        Toast.makeText(getApplicationContext(),intent.getStringExtra("firstName")+" "+intent.getStringExtra("lastName")//
                +" "+intent.getStringExtra("telephone")+" "//
                +intent.getStringExtra("email")+" "+intent.getStringExtra("socialNetwork")+" "+intent.getStringExtra("sex")//
                ,Toast.LENGTH_LONG ).show();//TODO: remove this
    }
}
