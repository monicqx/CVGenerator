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

public class SkillAdapter extends ArrayAdapter<Skill> {

    public SkillAdapter(Context context, int resource, List<Skill> objects) {
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.skills_list_template, null);
        }

        Skill skill = getItem(position);
        TextView skillTV = (TextView)convertView.findViewById(R.id.SkT_skillTV);
        TextView levelTV = (TextView) convertView.findViewById(R.id.SkT_levelTV);

        skillTV.setText(skill.getSkill());
        levelTV.setText(skill.getLevel());
        return convertView;
    }
}
