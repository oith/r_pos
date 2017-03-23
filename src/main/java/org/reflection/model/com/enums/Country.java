package org.reflection.model.com.enums;

public enum Country {
    BD("Bangladesh"),
    IT("Italy"),
    IN("India");

    private final String className;

    private Country(String className) {
        this.className = className;
    }

    public String getClassName() {
        return className;
    }
}
