package com.example.monic.cvgenerator;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.example.monic.cvgenerator.Classes.Language;
import com.example.monic.cvgenerator.Classes.LanguageFragment;
import com.example.monic.cvgenerator.Classes.LanguagesAdapter;
import com.example.monic.cvgenerator.Classes.SocialNetworksFragment;

public class LanguagesActivity extends AppCompatActivity implements LanguageFragment.OnFragmentInteractionListener{


    public static LanguagesAdapter languagesAdapter;
    public static ListView listView;

    private Button addNewLanguageBtn=null;
    private Button toITSkillsBtn=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_languages);

        findViewsById();

        createLanguagesListAdapter();

        addNewLanguageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAddLanguageDialog();
            }
        });
        toITSkillsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),ITSkillsActivity.class);
                startActivity(intent);
            }
        });
    }

    private void showAddLanguageDialog() {
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        LanguageFragment fragment = LanguageFragment.newInstance();
        fragment.show(fragmentTransaction, "dialog");
    }

    private void createLanguagesListAdapter() {
        languagesAdapter = new LanguagesAdapter(LanguagesActivity.this,R.layout.languages_list_template, CreateCVActivity.languagesArrayList);
        listView = (ListView)findViewById(R.id.L_languagesListView);
        listView.setAdapter(languagesAdapter);
    }

    private void findViewsById() {
        addNewLanguageBtn = (Button)findViewById(R.id.L_addNewLanguageBtn);
        toITSkillsBtn=(Button)findViewById(R.id.L_toITSkillsBtn);
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

}
