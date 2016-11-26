package com.example.monic.cvgenerator.Classes;

import android.app.DialogFragment;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.monic.cvgenerator.CreateCVActivity;
import com.example.monic.cvgenerator.LanguagesActivity;
import com.example.monic.cvgenerator.R;

public class LanguageFragment extends DialogFragment {
    private LanguageFragment.OnFragmentInteractionListener mListener;

    public LanguageFragment() {
    }

    public static LanguageFragment newInstance() {
        LanguageFragment fragment = new LanguageFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.languages_fragment, container, false);
        Button button = (Button) view.findViewById(R.id.Lf_addLanguageBtn);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText languageET = (EditText) view.findViewById(R.id.Lf_languageET);
                Spinner levelSp = (Spinner)view.findViewById(R.id.Lf_levelsSp);
                if (!languageET.getText().toString().isEmpty()) {
                    Language.LanguageLevel levelLanguage = Language.LanguageLevel.valueOf(levelSp.getSelectedItem().toString());
                    Language language = new Language(languageET.getText().toString(),levelLanguage);
                    CreateCVActivity.languagesArrayList.add(language);
                    LanguagesActivity.adapter.notifyDataSetChanged();
                }
                LanguageFragment.this.dismiss();
            }
        });
        return view;
    }

    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
           // LanguagesActivity.listView.setAdapter(LanguagesActivity.adapter);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof LanguageFragment.OnFragmentInteractionListener) {
            mListener = (LanguageFragment.OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;

    }

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }
}

