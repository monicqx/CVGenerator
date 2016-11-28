package com.example.monic.cvgenerator;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

public class OtherSkillsActivity extends AppCompatActivity {

    private Button addBtn = null;
    private ListView listView = null;
    private EditText skillET = null;
    private Button toCertificates = null;

    public void findViewById() {
        addBtn = (Button) findViewById(R.id.OS_addNewSkillBtn);
        listView = (ListView) findViewById(R.id.OS_otherSkillsLV);
        skillET = (EditText) findViewById(R.id.OS_otherSkillET);
        toCertificates = (Button) findViewById(R.id.OS_toCertificatesBtn);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other_skills);
        findViewById();

        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, CreateCVActivity.otherSkillsArrayList);
        listView.setAdapter(adapter);
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!(skillET.getText().toString().isEmpty())) {
                    CreateCVActivity.otherSkillsArrayList.add(skillET.getText().toString());
                    skillET.getText().clear();
                    adapter.notifyDataSetChanged();
                }
            }
        });
        toCertificates.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),CertificatesActivity.class);
                startActivity(intent);
            }
        });
    }
}
