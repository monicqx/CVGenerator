package com.example.monic.cvgenerator;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class OtherSkillsActivity extends AppCompatActivity {

    private Button addBtn = null;
    private ListView listView = null;
    private EditText skillET = null;
    private Button toCertificatesBtn = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other_skills);

        findViewsById();

        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, HomeActivity.profile.getOtherSkillsArrayList());
        listView.setAdapter(adapter);
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (controlsAreEmpty()) {
                    Toast.makeText(getApplicationContext(),"No empty fields allowed!",Toast.LENGTH_LONG).show();
                }else{
                    HomeActivity.profile.addOtherSkill(skillET.getText().toString());
                    clearControls();
                    adapter.notifyDataSetChanged();
                }
            }
        });
        toCertificatesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),CertificatesActivity.class);
                if(!controlsAreEmpty()){
                    HomeActivity.profile.addOtherSkill(skillET.getText().toString());
                    adapter.notifyDataSetChanged();
                    clearControls();
                }
                startActivity(intent);
            }
        });
    }

    public void findViewsById() {
        addBtn = (Button) findViewById(R.id.OS_addNewSkillBtn);
        listView = (ListView) findViewById(R.id.OS_otherSkillsLV);
        skillET = (EditText) findViewById(R.id.OS_otherSkillET);
        toCertificatesBtn = (Button) findViewById(R.id.OS_toCertificatesBtn);
    }

    private void clearControls() {
        skillET.getText().clear();
    }

    private boolean controlsAreEmpty() {
        if(skillET.getText().toString().isEmpty())
            return true;
        return false;
    }

}
