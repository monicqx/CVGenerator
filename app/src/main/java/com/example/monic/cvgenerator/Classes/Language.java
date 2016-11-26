package com.example.monic.cvgenerator.Classes;

/**
 * Created by monic on 26.11.2016.
 */

public class Language {
    private String language;
    public enum LanguageLevel{A1,A2,B1,B2,C1,C2};
    private LanguageLevel level;

    public Language(String language, LanguageLevel level) {
        this.language = language;
        this.level = level;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public LanguageLevel getLevel() {
        return level;
    }

    public void setLevel(LanguageLevel level) {
        this.level = level;
    }
}

