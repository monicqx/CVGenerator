package com.example.monic.cvgenerator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.monic.cvgenerator.Classes.Certificate;
import com.example.monic.cvgenerator.Classes.Education;
import com.example.monic.cvgenerator.Classes.Profile;
import com.example.monic.cvgenerator.Classes.Skill;
import com.example.monic.cvgenerator.Classes.WorkExperience;

import java.util.ArrayList;
import java.util.Map;

public class ViewCVActivity extends AppCompatActivity {

    private TextView nameTV=null;
    private TextView telephoneTV=null;
    private TextView emailTV=null;
    private TextView sexTV=null;
    private TextView birthdayTV=null;
    private TextView socialNetworksTV=null;
    private TextView educationTV=null;
    private TextView workExperienceTV=null;
    private TextView languagesTV=null;
    private TextView itSkillsTV=null;
    private TextView otherSkillsTV=null;
    private TextView certificatesTV=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_cv);

        findViewsById();

        if(CreateCVActivity.profile!=null && isProfileComplete(CreateCVActivity.profile)){
            populateTextViews(CreateCVActivity.profile);
        }else{
            Toast.makeText(getApplicationContext(),"You must create your CV first!",Toast.LENGTH_LONG).show();
            ((GridLayout)findViewById(R.id.VCV_gridLayout)).setVisibility(View.GONE);
        }
    }

    /**
     * @param profile
     * Checks if the mandatory fields of the CV have been instantiated
     */
    private boolean isProfileComplete(Profile profile) {
        if(profile.getFirstName()==null)
            return false;
        if(profile.getLastName()==null)
            return false;
        if(profile.getTelephone()==null)
            return false;
        if(profile.getEmail()==null)
            return false;
        if(profile.getBirthday()==null)
            return false;
        if(profile.getSex()==null)
            return false;
        return true;
    }

    /**
     * @param profile
     * Adds all the information from the Profile object to text views.
     */
    private void populateTextViews(Profile profile) {
        nameTV.setText(profile.getFirstName()+" "+profile.getLastName());
        telephoneTV.setText(profile.getTelephone());
        emailTV.setText(profile.getEmail());
        birthdayTV.setText(profile.getBirthday());

        if(profile.getSex().equals("F"))
            sexTV.setText("Female");
        else if(profile.getSex().equals("M"))
            sexTV.setText("Male");


        if(profile.getSocialNetworksMap().isEmpty()){
            socialNetworksTV.setVisibility(View.GONE);
            findViewById(R.id.VCV_socialNetworksTitle).setVisibility(View.GONE);
        }else {
            StringBuilder socialNetworkString = computeSocialNetworkText(profile.getSocialNetworksMap());
            socialNetworksTV.setText(socialNetworkString.toString());
        }

        if(profile.getEducationArrayList().isEmpty()){
            changeVisibilityOfControls(findViewById(R.id.VCV_educationLine),(TextView)findViewById(R.id.VCV_educationTV),educationTV);
        }else {
            StringBuilder educationString=computeEducationText(profile.getEducationArrayList());
            educationTV.setText(educationString);
        }

        if(profile.getWorkExperienceArrayList().isEmpty()){
            changeVisibilityOfControls(findViewById(R.id.VCV_workExperienceLine),(TextView)findViewById(R.id.VCV_workExperienceTitle),workExperienceTV);
        }else {
            StringBuilder workExperienceString = computeWorkExperienceText(profile.getWorkExperienceArrayList());
            workExperienceTV.setText(workExperienceString);
        }

        if(profile.getLanguagesArrayList().isEmpty()){
            changeVisibilityOfControls(findViewById(R.id.VCV_languagesLine),(TextView)findViewById(R.id.VCV_languagesTitle), languagesTV);
        }else {
            StringBuilder languagesString = computeLanguagesText(profile.getLanguagesArrayList());
            languagesTV.setText(languagesString);
        }

        if(profile.getItSkillsArrayList().isEmpty()){
            changeVisibilityOfControls(findViewById(R.id.VCV_itSkillsLine),(TextView)findViewById(R.id.VCV_itSkillsTitle), itSkillsTV);
        }else {
            StringBuilder itSkillsString = computeITSkillsText(profile.getItSkillsArrayList());
            itSkillsTV.setText(itSkillsString);
        }

        if(profile.getOtherSkillsArrayList().isEmpty()){
            changeVisibilityOfControls(findViewById(R.id.VCV_otherSkillsLine),(TextView)findViewById(R.id.VCV_otherSkillsTitle), otherSkillsTV);
        }else {
            StringBuilder otherSkillsString = computeOtherSkillsText(profile.getOtherSkillsArrayList());
            otherSkillsTV.setText(otherSkillsString);
        }

        if(profile.getCertificatesArrayList().isEmpty()){
            changeVisibilityOfControls(findViewById(R.id.VCV_certificatesLine),(TextView)findViewById(R.id.VCV_certificatesTitle), certificatesTV);
        }else {
            StringBuilder certificatesString = computeCertificatesText(profile.getCertificatesArrayList());
            certificatesTV.setText(certificatesString);
        }
    }

    private StringBuilder computeCertificatesText(ArrayList<Certificate> certificatesArrayList) {
        StringBuilder certificatesString=new StringBuilder();
        for(Certificate certificate:certificatesArrayList){
            certificatesString.append("Certificate: "+certificate.getName()+"\n");
            certificatesString.append("Year: "+certificate.getYear()+"\n");
            certificatesString.append("\n");
        }
        return certificatesString;
    }

    private StringBuilder computeOtherSkillsText(ArrayList<String> otherSkillsArrayList) {
        StringBuilder otherSkillsString=new StringBuilder();
        for(String skill:otherSkillsArrayList){
            otherSkillsString.append(skill+"\n");
        }
        return otherSkillsString;
    }

    private StringBuilder computeITSkillsText(ArrayList<Skill> itSkillsArrayList) {
        StringBuilder itSkillsString=new StringBuilder();
        for(Skill itSkill:itSkillsArrayList){
            itSkillsString.append("IT Skill: "+itSkill.getSkill()+"\n");
            itSkillsString.append("Level: "+itSkill.getLevel()+"\n");
            itSkillsString.append("\n");
        }
        return itSkillsString;
    }

    private StringBuilder computeLanguagesText(ArrayList<Skill> languagesArrayList) {
        StringBuilder languagesString=new StringBuilder();
        for(Skill language:languagesArrayList){
            languagesString.append("Language: "+language.getSkill()+"\n");
            languagesString.append("Level: "+language.getLevel()+"\n");
            languagesString.append("\n");
        }
        return languagesString;
    }

    private StringBuilder computeWorkExperienceText(ArrayList<WorkExperience> workExperienceArrayList) {
        StringBuilder workExperienceString=new StringBuilder();
        for(WorkExperience workExperience:workExperienceArrayList){
            workExperienceString.append("Position: "+workExperience.getPosition()+"\n");
            workExperienceString.append("Company: "+workExperience.getCompany()+"\n");
            workExperienceString.append("Period: "+workExperience.getStartYear()+" - "+workExperience.getEndYear()+"\n");
            if(!workExperience.getDescription().isEmpty())
                workExperienceString.append("Description: "+workExperience.getDescription()+"\n");
            workExperienceString.append("\n");
        }
        return workExperienceString;
    }

    private StringBuilder computeEducationText(ArrayList<Education> educationArrayList) {
        StringBuilder educationString=new StringBuilder();
        for(Education education:educationArrayList){
            educationString.append("School: "+education.getSchoolName()+"\n");
            educationString.append("Field of Study: "+education.getFieldOfStudy()+"\n");
            educationString.append("Type: "+education.getType()+"\n");
            educationString.append("Years: "+education.getStartYear()+" - "+education.getEndYear()+"\n");
            educationString.append("\n");
        }
        return educationString;
    }

    private StringBuilder computeSocialNetworkText(Map<String, String> socialNetworksMap) {
        StringBuilder socialNetworkString=new StringBuilder();

        for(Map.Entry<String,String> entry:socialNetworksMap.entrySet()){
            socialNetworkString.append(entry.getKey()+": "+entry.getValue()+"\n");
        }
        return socialNetworkString;
    }

    /**
     * @param view
     * @param textView1
     * @param textView2
     * Changes the visibility of views to GONE.
     */
    private void changeVisibilityOfControls(View view, TextView textView1, TextView textView2) {
        view.setVisibility(View.GONE);
        textView1.setVisibility(View.GONE);
        textView2.setVisibility(View.GONE);
    }

    private void findViewsById() {
        nameTV=(TextView)findViewById(R.id.VCV_nameTV);
        telephoneTV=(TextView)findViewById(R.id.VCV_telephoneTV);
        emailTV=(TextView)findViewById(R.id.VCV_emailTV);
        sexTV=(TextView)findViewById(R.id.VCV_sexTV);
        birthdayTV=(TextView)findViewById(R.id.VCV_birthdayTV);
        socialNetworksTV=(TextView)findViewById(R.id.VCV_socialNetworksTV);
        educationTV=(TextView)findViewById(R.id.VCV_educationTV);
        workExperienceTV=(TextView)findViewById(R.id.VCV_workExperienceTV);
        languagesTV=(TextView)findViewById(R.id.VCV_languagesTV);
        itSkillsTV=(TextView)findViewById(R.id.VCV_itSkillsTV);
        otherSkillsTV=(TextView)findViewById(R.id.VCV_otherSkillsTV);
        certificatesTV=(TextView)findViewById(R.id.VCV_certificatesTV);
    }
}
