package org.example;

public enum ParseTags {
    NUMCODE ("numcode"),
    CHARCODE ("charcode"),
    NOMINAL ("nominal"),
    NAME ("name"),
    VALUE ("value"),
    VALUTE ("valute");

    private final String description;

    ParseTags(String s) {
        this.description = s;
    }

    public String descr(){
        return this.description;
    }
}
