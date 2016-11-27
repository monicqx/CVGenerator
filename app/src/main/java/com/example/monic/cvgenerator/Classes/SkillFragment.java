package com.example.monic.cvgenerator.Classes;

import android.app.DialogFragment;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.monic.cvgenerator.CreateCVActivity;
import com.example.monic.cvgenerator.LanguagesActivity;
import com.example.monic.cvgenerator.R;

import java.util.ArrayList;

public class SkillFragment extends DialogFragment {
    private SkillFragment.OnFragmentInteractionListener mListener;

    public SkillFragment() {
    }

    public static SkillFragment newInstance() {
        SkillFragment fragment = new SkillFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.skills_fragment, container, false);

        Button addLanguageBtn = (Button) view.findViewById(R.id.SkF_addSkillBtn);

        addLanguageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText skillET = (EditText) view.findViewById(R.id.SkF_skillET);
                Spinner levelSp = (Spinner)view.findViewById(R.id.SkF_levelsSp);

                if(!emptyControls(skillET)){
                    onButtonPressed(Uri.parse(skillET.getText().toString()+";"+levelSp.getSelectedItem().toString()));
                }
                SkillFragment.this.dismiss();
            }
        });
        return view;
    }

    private boolean emptyControls(EditText skillET) {
        if(skillET.getText().toString().isEmpty())
            return true;
        return false;
    }

    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof SkillFragment.OnFragmentInteractionListener) {
            mListener = (SkillFragment.OnFragmentInteractionListener) context;
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

