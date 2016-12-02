package com.example.monic.cvgenerator;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.monic.cvgenerator.Classes.Certificate;

import java.util.ArrayList;

public class CertificatesActivity extends AppCompatActivity {

    private EditText certificateNameET = null;
    private Spinner certificateYearSpinner = null;
    private Button addCertificateBtn = null;
    private Button toViewCVBtn = null;
    private ListView certificatesLV = null;
    private ArrayList<String> certificatesTextArraylist=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_certificates);

        findViewsById();
        populateSpinner(certificateYearSpinner);

        certificatesTextArraylist=new ArrayList<>();
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, certificatesTextArraylist);
        certificatesLV.setAdapter(adapter);

        addCertificateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (controlsAreEmpty(certificateNameET)) {
                    Toast.makeText(getApplicationContext(), "No empty fields allowed!", Toast.LENGTH_LONG);
                } else {
                    Certificate certificate=new Certificate(certificateNameET.getText().toString(),certificateYearSpinner.getSelectedItem().toString());
                    CreateCVActivity.certificatesArrayList.add(certificate);
                    certificatesTextArraylist.add(certificate.toString());
                    clearControls(certificateNameET,certificateYearSpinner);
                    adapter.notifyDataSetChanged();
                }
            }
        });

        toViewCVBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),CertificatesActivity.class);
                if(!controlsAreEmpty(certificateNameET)){
                    Certificate certificate=new Certificate(certificateNameET.getText().toString(),certificateYearSpinner.getSelectedItem().toString());
                    CreateCVActivity.certificatesArrayList.add(certificate);
                    certificatesTextArraylist.add(certificate.toString());
                    clearControls(certificateNameET,certificateYearSpinner);
                    adapter.notifyDataSetChanged();
                }
                startActivity(intent);
            }
        });
    }

    private void clearControls(EditText certificateNameET, Spinner certificateYearSpinner) {
        certificateNameET.getText().clear();
        certificateYearSpinner.setSelection(0);
    }

    private boolean controlsAreEmpty(EditText certificateNameET) {
        if (certificateNameET.getText().toString().isEmpty())
            return true;
        return false;
    }

    public void findViewsById() {
        addCertificateBtn = (Button) findViewById(R.id.CE_addCertificateBtn);
        certificatesLV = (ListView) findViewById(R.id.CE_CertificatesLV);
        certificateYearSpinner = (Spinner) findViewById(R.id.CE_yearSpinner);
        toViewCVBtn = (Button) findViewById(R.id.CE_toViewCVBtn);
        certificateNameET = (EditText) findViewById(R.id.CE_nameCertificateET);
        toViewCVBtn=(Button)findViewById(R.id.CE_toViewCVBtn);
    }

    private void populateSpinner(Spinner certificatesSpinner) {
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(),
                R.layout.support_simple_spinner_dropdown_item, HomeActivity.yearsStart);
        certificatesSpinner.setAdapter(adapter);
    }
}
