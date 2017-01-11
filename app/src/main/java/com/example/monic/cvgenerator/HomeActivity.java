package com.example.monic.cvgenerator;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.monic.cvgenerator.Classes.Certificate;
import com.example.monic.cvgenerator.Classes.Education;
import com.example.monic.cvgenerator.Classes.Profile;
import com.example.monic.cvgenerator.Classes.Skill;
import com.example.monic.cvgenerator.Classes.WorkExperience;
import com.example.monic.cvgenerator.Database.DatabaseAdapter;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class HomeActivity extends AppCompatActivity {

    //Date pentru stocarea anilor
    private Calendar calendar = Calendar.getInstance();
    private int currentYear = calendar.get(Calendar.YEAR);
    private Button menuBtn=null;
    private Spinner userSp=null;
    private Button newCVBtn=null;

    public static ArrayList<String> yearsStart = new ArrayList<>();
    public static ArrayList<String> yearsEndEducation = new ArrayList<>();
    public static Profile profile=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        findViewsById();
        populateSpinner();
        computeYearsArray();

        SharedPreferences preferences = this.getSharedPreferences("lastUser",MODE_PRIVATE);
        String currentUser = preferences.getString("firstName","unknown")+" ; "+preferences.getString("lastName","unknown");
        for(int i=0;i<userSp.getCount();i++)
            if(userSp.getItemAtPosition(i).equals(currentUser)){
                userSp.setSelection(i);
                break;
            }
        //deleteAllFromDatabase();

        menuBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String selection = null;
                if(userSp.getSelectedItem()!=null)
                    selection=userSp.getSelectedItem().toString();
                if(selection!=null) {
                    getInfoFromDB(selection);
                }
                Intent intent = new Intent(getApplicationContext(), MenuActivity.class);
                startActivity(intent);
            }
        });
        newCVBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),CreateCVActivity.class);
                startActivity(intent);
            }
        });

    }

    /**
     * Deletes all entries from all tables in the database
     */
    private void deleteAllFromDatabase() {
//        DatabaseAdapter dbAdapter = new DatabaseAdapter(getApplicationContext());
//        dbAdapter.openConnection();
//        List<String> users=dbAdapter.getUsers();
//        for(String user:users){
//            String[] names=user.split(" ; ");
//
//            int userId=dbAdapter.getUserIdByNames(names[0],names[1]);
//            dbAdapter.deleteAllCvsOfAUser(userId);
//        }
//        dbAdapter.deleteUsers();
    }


    /**
     * Creates a profile with the data available in the database
     * @param selection
     */
    private void getInfoFromDB(String selection) {
        String[] names=selection.split(" ; ");

        DatabaseAdapter dbAdapter = new DatabaseAdapter(getApplicationContext());
        dbAdapter.openConnection();

        int userId=dbAdapter.getUserIdByNames(names[0],names[1]);
        String xml=dbAdapter.getCV(userId);

        dbAdapter.closeConnection();
        parseXML(xml);
    }

    private void parseXML(String xml) {
        profile=new Profile();
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            InputSource is = new InputSource(new StringReader(xml));
            Document document = builder.parse(is);

            if(document!=null){

                Node node = document.getElementsByTagName("firstName").item(0);
                profile.setFirstName(node.getTextContent());
                node=document.getElementsByTagName("lastName").item(0);
                profile.setLastName(node.getTextContent());
                node=document.getElementsByTagName("telephone").item(0);
                profile.setTelephone(node.getTextContent());
                node=document.getElementsByTagName("email").item(0);
                profile.setEmail(node.getTextContent());
                node=document.getElementsByTagName("sex").item(0);
                profile.setSex(node.getTextContent());
                node=document.getElementsByTagName("birthday").item(0);
                profile.setBirthday(node.getTextContent());
                NodeList nodeList=document.getElementsByTagName("socialNetwork");
                for(int i=0;i<nodeList.getLength();i++){
                    node=nodeList.item(i);
                    if(node!=null && node.getNodeType()==Node.ELEMENT_NODE){
                        Element element = (Element)node;
                        profile.addSocialNetwork(element.getAttribute("name"),element.getAttribute("url"));
                    }
                }
                nodeList=document.getElementsByTagName("education");
                for(int i=0;i<nodeList.getLength();i++){
                    node=nodeList.item(i);
                    if(node!=null && node.getNodeType()==Node.ELEMENT_NODE){
                        Element element = (Element)node;
                        Education education = new Education(element.getAttribute("schoolName"),element.getAttribute("fieldOfStudy"), element.getAttribute("startYear"), element.getAttribute("endYear"), element.getAttribute("type"));
                        profile.addEducation(education);
                    }
                }
                nodeList=document.getElementsByTagName("workExperience");
                for(int i=0;i<nodeList.getLength();i++){
                    node=nodeList.item(i);
                    if(node!=null && node.getNodeType()==Node.ELEMENT_NODE){
                        Element element = (Element)node;
                        WorkExperience workExperience = new  WorkExperience(element.getAttribute("position"), element.getAttribute("company"), element.getAttribute("startYear"), element.getAttribute("endYear"), element.getAttribute("description")) ;
                        profile.addWorkExperience(workExperience);
                    }
                }
                nodeList=document.getElementsByTagName("language");
                for(int i=0;i<nodeList.getLength();i++){
                    node=nodeList.item(i);
                    if(node!=null && node.getNodeType()==Node.ELEMENT_NODE){
                        Element element = (Element)node;
                        Skill skill = new Skill(element.getAttribute("name"), element.getAttribute("level"));
                        profile.addLanguage(skill);
                    }
                }
                nodeList=document.getElementsByTagName("itSkill");
                for(int i=0;i<nodeList.getLength();i++){
                    node=nodeList.item(i);
                    if(node!=null && node.getNodeType()==Node.ELEMENT_NODE){
                        Element element = (Element)node;
                        Skill skill = new Skill(element.getAttribute("name"), element.getAttribute("level"));
                        profile.addITSkill(skill);
                    }
                }
                nodeList=document.getElementsByTagName("otherSkill");
                for(int i=0;i<nodeList.getLength();i++){
                    node=nodeList.item(i);
                    if(node!=null && node.getNodeType()==Node.ELEMENT_NODE){
                        Element element = (Element)node;
                        profile.addOtherSkill(element.getAttribute("name"));
                    }
                }
                nodeList=document.getElementsByTagName("certificate");
                for(int i=0;i<nodeList.getLength();i++){
                    node=nodeList.item(i);
                    if(node!=null && node.getNodeType()==Node.ELEMENT_NODE){
                        Element element = (Element)node;
                        Certificate certificate = new Certificate(element.getAttribute("name"), element.getAttribute("year"));
                        profile.addCertificate(certificate);
                    }
                }
            }
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * Populates spinner with the first name and last name of the cv's in the database
     */
    private void populateSpinner() {
        DatabaseAdapter dbAdapter = new DatabaseAdapter(getApplicationContext());
        dbAdapter.openConnection();
        List<String> users=dbAdapter.getUsers();
        dbAdapter.closeConnection();

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(),
                R.layout.support_simple_spinner_dropdown_item, users);
        userSp.setAdapter(adapter);
    }

    private void findViewsById() {
        menuBtn=(Button)findViewById(R.id.H_menuBtn);
        userSp=(Spinner)findViewById(R.id.H_userSp);
        newCVBtn=(Button)findViewById(R.id.H_newBtn);
    }

    private void computeYearsArray() {
        for (int i = currentYear+10;i>currentYear;i--){
            yearsEndEducation.add(i+" ");
        }
        for(int i=currentYear;i>=1940;i--){
            yearsStart.add(i+"");
            yearsEndEducation.add(i+"");
        }
    }
}
