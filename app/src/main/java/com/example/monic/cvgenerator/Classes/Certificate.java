package com.example.monic.cvgenerator.Classes;


public class Certificate {

    private String name;
    private String year;

    public Certificate(String name, String year) {
        this.name = name;
        this.year = year;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    @Override
    public String toString() {
        return "\'"+name + "\' - " +
                 year;
    }
}
