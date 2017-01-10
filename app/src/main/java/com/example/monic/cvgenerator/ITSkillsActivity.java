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
import com.example.monic.cvgenerator.Classes.SkillAdapter;
import com.example.monic.cvgenerator.Classes.SkillFragment;

public class ITSkillsActivity extends AppCompatActivity implements SkillFragment.OnFragmentInteractionListener{
    public static SkillAdapter skillAdapter;
    public static ListView listView;

    private Button addNewITSkillBtn=null;
    private Button toOtherSkillsBtn=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_itskills);

        findViewsById();
        createITSkillsListAdapter();

        addNewITSkillBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAddITSkillDialog();
            }
        });

        toOtherSkillsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),OtherSkillsActivity.class);
                startActivity(intent);
            }
        });
    }

    private void showAddITSkillDialog() {
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        SkillFragment fragment = SkillFragment.newInstance();
        fragment.show(fragmentTransaction, "dialog");
    }


    private void createITSkillsListAdapter() {
        skillAdapter = new SkillAdapter(ITSkillsActivity.this,R.layout.skills_list_template, HomeActivity.profile.getItSkillsArrayList());
        listView = (ListView)findViewById(R.id.IT_itSkillsListView);
        listView.setAdapter(skillAdapter);
    }

    private void findViewsById() {
        addNewITSkillBtn=(Button)findViewById(R.id.IT_addNewITSkillBtn);
        toOtherSkillsBtn=(Button)findViewById(R.id.IT_toOtherSkillsBtn);
    }

    @Override
    public void onFragmentInteraction(Uri uri) {
        String string=uri.toString();
        String[] token=string.split(";");
        if (token.length == 2) {
            Skill skill=new Skill(token[0],token[1]);
            HomeActivity.profile.addITSkill(skill);
            skillAdapter.notifyDataSetChanged();
        }else{
            Log.w("itskills","IT skills could not be added to arrayList.");
        }
    }
}
