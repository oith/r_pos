package org.reflection.model.com.enums;

public enum Locale {
    bn_BD("Bangladeshi Bangla"),
    bn_IN("Indian Bangla"),
    en("English Global"),
    es("Spanish"),
    fr("Frnch"),
    hi_IN("Indian Hindi"),
    it("Italyian");

    private final String name;

    private Locale(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
