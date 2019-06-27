package com.senon.contactbar;

import java.util.Comparator;


public class PinyinComparator implements Comparator<PersonData> {
    public int compare(PersonData o1, PersonData o2) {
        if (o1.getLetters().equals("@")
                || o2.getLetters().equals("#")) {
            return -1;
        } else if (o1.getLetters().equals("#")
                   || o2.getLetters().equals("@")) {
            return 1;
        } else {
            return o1.getLetters().compareTo(o2.getLetters());
        }
    }

}
