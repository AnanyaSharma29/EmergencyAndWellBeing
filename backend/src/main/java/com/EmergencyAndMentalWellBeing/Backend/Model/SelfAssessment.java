package com.EmergencyAndMentalWellBeing.Backend.Model;


public class SelfAssessment {

    private String mood;
    private String anxiety;
    private String stress;
    private String sleep;

    // Getters and Setters
    public String getMood() {
        return mood;
    }

    public void setMood(String mood) {
        this.mood = mood;
    }

    public String getAnxiety() {
        return anxiety;
    }

    public void setAnxiety(String anxiety) {
        this.anxiety = anxiety;
    }

    public String getStress() {
        return stress;
    }

    public void setStress(String stress) {
        this.stress = stress;
    }

    public String getSleep() {
        return sleep;
    }

    public void setSleep(String sleep) {
        this.sleep = sleep;
    }

    @Override
    public String toString() {
        return "SelfAssessment{" +
                "mood='" + mood + '\'' +
                ", anxiety='" + anxiety + '\'' +
                ", stress='" + stress + '\'' +
                ", sleep='" + sleep + '\'' +
                '}';
    }
}

