package com.example.monic.cvgenerator;

import android.app.FragmentTransaction;
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
    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    public static LanguagesAdapter adapter;
    public static ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_languages);

        adapter = new LanguagesAdapter(LanguagesActivity.this,R.layout.languages_list_template, CreateCVActivity.languagesArrayList);
        listView = (ListView)findViewById(R.id.activity_languages);
        listView.setAdapter(adapter);

        Button addNewLanguage = (Button)findViewById(R.id.L_addNewLanguageBtn);
        addNewLanguage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction t = getFragmentManager().beginTransaction();
                LanguageFragment fragment = LanguageFragment.newInstance();
                fragment.show(t, "dialog");
            }
        });

    }
}
