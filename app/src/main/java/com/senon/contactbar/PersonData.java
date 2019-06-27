package com.senon.contactbar;

public class PersonData {

    private String name;
    private String letters;

    public PersonData() {
    }

    public PersonData(String name, String letters) {
        this.name = name;
        this.letters = letters;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLetters() {
        return letters;
    }

    public void setLetters(String letters) {
        this.letters = letters;
    }
}
