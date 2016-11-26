package com.example.monic.cvgenerator.Classes;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.monic.cvgenerator.R;

import java.util.List;

/**
 * Created by monic on 26.11.2016.
 */

public class LanguagesAdapter extends ArrayAdapter<Language> {

    public LanguagesAdapter(Context context, int resource, List<Language> objects) {
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.languages_list_template, null);
        }

        Language language = getItem(position);
        TextView languageTV = (TextView)convertView.findViewById(R.id.L_languageTV);
        TextView levelTV = (TextView) convertView.findViewById(R.id.L_levelTV);

        languageTV.setText(language.getLanguage());
        levelTV.setText(language.getLevel()+"");
        return convertView;
    }
}
