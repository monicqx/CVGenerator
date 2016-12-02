package com.example.monic.cvgenerator.Classes;

public class Skill {
    private String skill;
    private String level;

    public Skill(String skill, String level) {
        this.skill = skill;
        this.level = level;
    }

    public String getSkill() {
        return skill;
    }

    public void setSkill(String skill) {
        this.skill = skill;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    @Override
    public String toString() {
        return "Skill{" +
                "skill='" + skill + '\'' +
                ", level='" + level + '\'' +
                '}';
    }
}

