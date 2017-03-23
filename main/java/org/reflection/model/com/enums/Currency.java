package org.reflection.model.com.enums;

public enum Currency {

    BDT("Bangladesh Taka"),
    INR("Indian Rupee"),
    EURO("Euro"),
    USD("United States Dollar");

    private final String className;

    private Currency(String className) {
        this.className = className;
    }

    public String getClassName() {
        return className;
    }
}
