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
import android.widget.TextView;

import com.example.monic.cvgenerator.CreateCVActivity;
import com.example.monic.cvgenerator.HomeActivity;
import com.example.monic.cvgenerator.R;


public class SocialNetworksFragment extends DialogFragment {
    private OnFragmentInteractionListener mListener;

    public SocialNetworksFragment() {
        // Required empty public constructor
    }

    public static SocialNetworksFragment newInstance() {
        SocialNetworksFragment fragment = new SocialNetworksFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.social_networks_fragment, container, false);
        Button button = (Button) view.findViewById(R.id.Sf_addSocialNetworksBtn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                EditText socialNetwork1 = (EditText) view.findViewById(R.id.Sf_socialnetwork1ET);
                EditText socialNetwork2 = (EditText) view.findViewById(R.id.Sf_socialnetwork2ET);
                EditText socialNetwork3 = (EditText) view.findViewById(R.id.Sf_socialnetwork3ET);
                EditText socialNetwork4 = (EditText) view.findViewById(R.id.Sf_socialnetwork4ET);
                if (!socialNetwork1.getText().toString().isEmpty()) {
                    HomeActivity.profile.addSocialNetwork(((TextView) view.findViewById(R.id.Sf_socialNetwork1TV)).getText().toString(), socialNetwork1.getText().toString());
                }
                if (!socialNetwork2.getText().toString().isEmpty()) {
                    HomeActivity.profile.addSocialNetwork(((TextView) view.findViewById(R.id.Sf_socialNetwork2TV)).getText().toString(), socialNetwork2.getText().toString());
                }
                if (!socialNetwork3.getText().toString().isEmpty()) {
                    HomeActivity.profile.addSocialNetwork(((TextView) view.findViewById(R.id.Sf_socialNetwork3TV)).getText().toString(), socialNetwork3.getText().toString());
                }
                if (!socialNetwork4.getText().toString().isEmpty()) {
                    HomeActivity.profile.addSocialNetwork(((TextView) view.findViewById(R.id.Sf_socialNetwork4TV)).getText().toString(), socialNetwork4.getText().toString());
                }
                SocialNetworksFragment.this.dismiss();
            }
        });
        return view;
    }
/*
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }*/

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
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
