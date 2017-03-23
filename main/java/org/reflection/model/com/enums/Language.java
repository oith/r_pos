package org.reflection.model.com.enums;

public enum Language {

    bn("Bangla"),
    hi("Hindi"),
    en("English");

    private final String className;

    private Language(String className) {
        this.className = className;
    }

    public String getClassName() {
        return className;
    }
}
