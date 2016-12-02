package com.example.monic.cvgenerator;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.example.monic.cvgenerator.Classes.Skill;
import com.example.monic.cvgenerator.Classes.SkillFragment;
import com.example.monic.cvgenerator.Classes.SkillAdapter;

import java.util.logging.Logger;

public class LanguagesActivity extends AppCompatActivity implements SkillFragment.OnFragmentInteractionListener{


    public static SkillAdapter skillAdapter;
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
        SkillFragment fragment = SkillFragment.newInstance();
        fragment.show(fragmentTransaction, "dialog");
    }

    private void createLanguagesListAdapter() {
        skillAdapter = new SkillAdapter(LanguagesActivity.this,R.layout.skills_list_template, CreateCVActivity.profile.getLanguagesArrayList());
        listView = (ListView)findViewById(R.id.L_languagesListView);
        listView.setAdapter(skillAdapter);
    }

    private void findViewsById() {
        addNewLanguageBtn = (Button)findViewById(R.id.L_addNewLanguageBtn);
        toITSkillsBtn=(Button)findViewById(R.id.L_toITSkillsBtn);
    }

    @Override
    public void onFragmentInteraction(Uri uri) {
        String string=uri.toString();
        String[] token=string.split(";");
        if (token.length == 2) {
            Skill skill=new Skill(token[0],token[1]);
            CreateCVActivity.profile.addLanguage(skill);
            skillAdapter.notifyDataSetChanged();
        }else{
            Log.w("languages","Language could not be added to arrayList.");
        }

    }

}
