package com.example.monic.cvgenerator.Classes;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Profile {
    private String firstName;
    private String lastName;
    private String telephone;
    private String email;
    private String sex;
    private String birthday;
    private Map<String, String> socialNetworksMap;
    private ArrayList<Education> educationArrayList;
    private ArrayList<WorkExperience> workExperienceArrayList;
    private ArrayList<Skill> languagesArrayList;
    private ArrayList<Skill> itSkillsArrayList;
    private ArrayList<String> otherSkillsArrayList;
    private ArrayList<Certificate> certificatesArrayList;

    public Profile() {
        socialNetworksMap = new HashMap<>();
        educationArrayList = new ArrayList<>();
        workExperienceArrayList = new ArrayList<>();
        languagesArrayList = new ArrayList<>();
        itSkillsArrayList = new ArrayList<>();
        otherSkillsArrayList = new ArrayList<>();
        certificatesArrayList = new ArrayList<>();
    }

    public void addSocialNetwork(String socialNetworkName, String url) {
        socialNetworksMap.put(socialNetworkName, url);
    }

    public void addEducation(Education education) {
        educationArrayList.add(education);
    }

    public void addWorkExperience(WorkExperience workExperience){
        workExperienceArrayList.add(workExperience);
    }

    public void addLanguage(Skill language) {
        languagesArrayList.add(language);
    }

    public void addITSkill(Skill itSkill) {
        itSkillsArrayList.add(itSkill);
    }

    public void addOtherSkill(String skill) {
        otherSkillsArrayList.add(skill);
    }

    public void addCertificate(Certificate certificate) {
        certificatesArrayList.add(certificate);
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public Map<String, String> getSocialNetworksMap() {
        return socialNetworksMap;
    }

    public void setSocialNetworksMap(Map<String, String> socialNetworksMap) {
        this.socialNetworksMap = socialNetworksMap;
    }

    public ArrayList<Education> getEducationArrayList() {
        return educationArrayList;
    }

    public void setEducationArrayList(ArrayList<Education> educationArrayList) {
        this.educationArrayList = educationArrayList;
    }

    public ArrayList<WorkExperience> getWorkExperienceArrayList() {
        return workExperienceArrayList;
    }

    public void setWorkExperienceArrayList(ArrayList<WorkExperience> workExperienceArrayList) {
        this.workExperienceArrayList = workExperienceArrayList;
    }

    public ArrayList<Skill> getLanguagesArrayList() {
        return languagesArrayList;
    }

    public void setLanguagesArrayList(ArrayList<Skill> languagesArrayList) {
        this.languagesArrayList = languagesArrayList;
    }

    public ArrayList<Skill> getItSkillsArrayList() {
        return itSkillsArrayList;
    }

    public void setItSkillsArrayList(ArrayList<Skill> itSkillsArrayList) {
        this.itSkillsArrayList = itSkillsArrayList;
    }

    public ArrayList<String> getOtherSkillsArrayList() {
        return otherSkillsArrayList;
    }

    public void setOtherSkillsArrayList(ArrayList<String> otherSkillsArrayList) {
        this.otherSkillsArrayList = otherSkillsArrayList;
    }

    public ArrayList<Certificate> getCertificatesArrayList() {
        return certificatesArrayList;
    }

    public void setCertificatesArrayList(ArrayList<Certificate> certificatesArrayList) {
        this.certificatesArrayList = certificatesArrayList;
    }

    @Override
    public String toString() {
        return "Profile{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", telephone='" + telephone + '\'' +
                ", email='" + email + '\'' +
                ", sex='" + sex + '\'' +
                ", birthday='" + birthday + '\'' +
                ", socialNetworksMap=" + socialNetworksMap +
                ", educationArrayList=" + educationArrayList +
                ", workExperienceArrayList=" + workExperienceArrayList +
                ", languagesArrayList=" + languagesArrayList +
                ", itSkillsArrayList=" + itSkillsArrayList +
                ", otherSkillsArrayList=" + otherSkillsArrayList +
                ", certificatesArrayList=" + certificatesArrayList +
                '}';
    }
}
