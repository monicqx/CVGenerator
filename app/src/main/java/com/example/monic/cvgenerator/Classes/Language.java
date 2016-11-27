package com.example.monic.cvgenerator.Classes;

/**
 * Created by monic on 26.11.2016.
 */

public class Language {
    private String language;
    private String level;

    public Language(String language, String level) {
        this.language = language;
        this.level = level;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }
}

