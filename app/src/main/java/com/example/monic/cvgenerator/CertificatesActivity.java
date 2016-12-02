package com.example.monic.cvgenerator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.List;

public class CertificatesActivity extends AppCompatActivity {

    private Button addCertificateBtn = null;
    private Spinner certificatesSpinner = null;
    private Button toViewCV = null;
    private ListView certificatesLV = null;
    private EditText nameET = null;

    public void findViewsById() {
        addCertificateBtn = (Button) findViewById(R.id.CE_addCertificateBtn);
        certificatesLV = (ListView) findViewById(R.id.CE_CertificatesLV);
        certificatesSpinner = (Spinner) findViewById(R.id.CE_yearSpinner);
        toViewCV = (Button) findViewById(R.id.CE_toViewCVBtn);
        nameET = (EditText) findViewById(R.id.CE_nameCertificateET);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_certificates);
        findViewsById();
        populateSpinner(certificatesSpinner);

        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, CreateCVActivity.certificatesArrayList);
        certificatesLV.setAdapter(adapter);

        addCertificateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!nameET.getText().toString().isEmpty()){
                    String certificate = '\''+nameET.getText().toString()+"\' - "+certificatesSpinner.getSelectedItem().toString();
                    CreateCVActivity.certificatesArrayList.add(certificate);
                    nameET.getText().clear();
                    certificatesSpinner.setSelection(0);
                    adapter.notifyDataSetChanged();
                }
            }
        });
    }

    private void populateSpinner(Spinner certificatesSpinner) {
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(),
                R.layout.support_simple_spinner_dropdown_item, HomeActivity.yearsStart);
        certificatesSpinner.setAdapter(adapter);
    }
}
