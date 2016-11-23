package com.example.monic.cvgenerator.Classes;

/**
 * Created by Bianca on 23.11.2016.
 */

public class WorkExperience {
    private String position;
    private String company;
    private String startYear;
    private String endYear;
    private String description;

    public WorkExperience(String position, String company, String startYear, String endYear, String description) {
        this.position = position;
        this.company = company;
        this.startYear = startYear;
        this.endYear = endYear;
        this.description = description;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getStartYear() {
        return startYear;
    }

    public void setStartYear(String startYear) {
        this.startYear = startYear;
    }

    public String getEndYear() {
        return endYear;
    }

    public void setEndYear(String endYear) {
        this.endYear = endYear;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "WorkExperience{" +
                "position='" + position + '\'' +
                ", company='" + company + '\'' +
                ", startYear='" + startYear + '\'' +
                ", endYear='" + endYear + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
