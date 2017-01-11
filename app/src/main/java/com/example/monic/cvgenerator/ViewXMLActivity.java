package com.example.monic.cvgenerator;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Xml;
import android.widget.TextView;
import android.widget.Toast;

import com.example.monic.cvgenerator.Classes.Certificate;
import com.example.monic.cvgenerator.Classes.Education;
import com.example.monic.cvgenerator.Classes.Skill;
import com.example.monic.cvgenerator.Classes.WorkExperience;
import com.example.monic.cvgenerator.Database.DatabaseAdapter;

import org.xmlpull.v1.XmlSerializer;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.util.Map;

public class ViewXMLActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_xml);
        if (HomeActivity.profile != null && ViewCVActivity.isProfileComplete(HomeActivity.profile)) {
            String xmlText=createXML();
            ((TextView)findViewById(R.id.VXML_text_TVs)).setText(xmlText);
            insertDataIntoDatabase(xmlText);
        } else {
            Toast.makeText(getApplicationContext(), "Please create a CV!", Toast.LENGTH_LONG).show();
        }
    }

    /**
     * @param xmlText
     * Adds the XML and the name of the user into the database.
     */
    private void insertDataIntoDatabase(String xmlText) {
        DatabaseAdapter dbAdapter = new DatabaseAdapter(getApplicationContext());
        dbAdapter.openConnection();

        Integer userId=dbAdapter.getUserIdByNames(HomeActivity.profile.getFirstName(),HomeActivity.profile.getLastName());
        if(userId!=null){   //daca user-ul exista deja in baza de date
            dbAdapter.deleteAllCvsOfAUser(userId);  //se sterge cv-ul existent al user-ului
            dbAdapter.insertCV(xmlText,userId);     //se adauga noul cv al user-ului
        }
        else {  //daca user-ul nu exista in baza de date
            dbAdapter.insertUser(HomeActivity.profile.getFirstName(), HomeActivity.profile.getLastName());  //se adauga un nou user in baza de date
            userId=dbAdapter.getUserIdByNames(HomeActivity.profile.getFirstName(),HomeActivity.profile.getLastName());  //interogarea bazei de date pentru a obtine id-ul user-ului
            dbAdapter.insertCV(xmlText,userId); //adaugare cv corespunzator user-ului
        }
        dbAdapter.closeConnection();
    }

    /**
     * Creates XML with the data from profile object.
     */
    private String createXML() {
        XmlSerializer serializer = Xml.newSerializer();
        StringWriter writer = new StringWriter();

        try {
            serializer.setOutput(writer);
            serializer.startDocument("UTF-8", true);
            serializer.startTag("", "profile");
            serializer.startTag("", "firstName");
            serializer.text(HomeActivity.profile.getFirstName());
            serializer.endTag("", "firstName");
            serializer.startTag("", "lastName");
            serializer.text(HomeActivity.profile.getLastName());
            serializer.endTag("", "lastName");
            serializer.startTag("", "telephone");
            serializer.text(HomeActivity.profile.getTelephone());
            serializer.endTag("","telephone");
            serializer.startTag("", "email");
            serializer.text(HomeActivity.profile.getEmail());
            serializer.endTag("", "email");
            serializer.startTag("", "sex");
            serializer.text(HomeActivity.profile.getSex());
            serializer.endTag("", "sex");
            serializer.startTag("", "birthday");
            serializer.text(HomeActivity.profile.getBirthday());
            serializer.endTag("", "birthday");
            if (!HomeActivity.profile.getSocialNetworksMap().isEmpty()) {
                for (Map.Entry<String, String> entry : HomeActivity.profile.getSocialNetworksMap().entrySet()) {
                    serializer.startTag("", "socialNetwork");
                    serializer.attribute("","name",entry.getKey());
                    serializer.attribute("","url",entry.getValue());
                    serializer.endTag("", "socialNetwork");
                }
            }
            if (!HomeActivity.profile.getEducationArrayList().isEmpty()) {
                for (Education education : HomeActivity.profile.getEducationArrayList()) {
                    serializer.startTag("", "education");
                    serializer.attribute("","schoolName",education.getSchoolName());
                    serializer.attribute("","fieldOfStudy",education.getFieldOfStudy());
                    serializer.attribute("","startYear",education.getStartYear());
                    serializer.attribute("","endYear",education.getEndYear());
                    serializer.attribute("","type",education.getType());
                    serializer.endTag("", "education");
                }
            }
            if (!HomeActivity.profile.getWorkExperienceArrayList().isEmpty()) {
                for (WorkExperience workExperience : HomeActivity.profile.getWorkExperienceArrayList()) {
                    serializer.startTag("", "workExperience");
                    serializer.attribute("","position",workExperience.getPosition());
                    serializer.attribute("","company",workExperience.getCompany());
                    serializer.attribute("","startYear",workExperience.getStartYear());
                    serializer.attribute("","endYear",workExperience.getEndYear());
                    serializer.attribute("","description",workExperience.getDescription());
                    serializer.endTag("", "workExperience");
                }
            }
            if (!HomeActivity.profile.getLanguagesArrayList().isEmpty()) {
                for (Skill language : HomeActivity.profile.getLanguagesArrayList()) {
                    serializer.startTag("", "language");
                    serializer.attribute("","name",language.getSkill());
                    serializer.attribute("","level",language.getLevel());
                    serializer.endTag("", "language");
                }
            }
            if (!HomeActivity.profile.getItSkillsArrayList().isEmpty()) {
                for (Skill itSkill : HomeActivity.profile.getItSkillsArrayList()) {
                    serializer.startTag("", "itSkill");
                    serializer.attribute("","name",itSkill.getSkill());
                    serializer.attribute("","level",itSkill.getLevel());
                    serializer.endTag("", "itSkill");
                }
            }
            if(!HomeActivity.profile.getOtherSkillsArrayList().isEmpty()) {
                for (String otherSkill : HomeActivity.profile.getOtherSkillsArrayList()) {
                    serializer.startTag("", "otherSkill");
                    serializer.attribute("","name",otherSkill);
                    serializer.endTag("", "otherSkill");
                }
            }
            if(!HomeActivity.profile.getCertificatesArrayList().isEmpty()) {
                for (Certificate certificate : HomeActivity.profile.getCertificatesArrayList()) {
                    serializer.startTag("", "certificate");
                    serializer.attribute("","name",certificate.getName());
                    serializer.attribute("","year",certificate.getYear());
                    serializer.endTag("", "certificate");
                }
            }
            serializer.endTag("", "profile");
            serializer.endDocument();

            SharedPreferences sharedPreferences = getSharedPreferences("lastUser",MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("firstName",HomeActivity.profile.getFirstName());
            editor.putString("lastName",HomeActivity.profile.getLastName());
            editor.commit();
            return writer.toString();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
