package org.reflection.controller;

import java.beans.PropertyEditorSupport;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

public class _DatePropertyEditor extends PropertyEditorSupport {

    private final SimpleDateFormat dateFormatTs = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
    private final SimpleDateFormat dateFormatD = new SimpleDateFormat("dd/MM/yyyy");
    private final SimpleDateFormat dateFormatTm = new SimpleDateFormat("HH:mm");

    @Override
    public String getAsText() {

        Object value = getValue();
        if (value == null) {
            return null;
        }

        if (value instanceof Time) {
            return dateFormatTm.format(value);
        } else if (value instanceof Timestamp) {
            return dateFormatTs.format(value);
        } else if (value instanceof Date) {
            return dateFormatD.format(value);
        }

        return value.toString();
    }

    @Override
    public void setAsText(String txt) throws IllegalArgumentException {

        if (txt == null || txt.trim().isEmpty()) {
            setValue(null);
            return;
        }

        String text = txt.trim();
        int textLen = text.length();

        System.out.println("dukeche Date yy1:>" + text + "<");

        try {

            switch (textLen) {
                case 19: {
                    setValue(new Timestamp(dateFormatTs.parse(text).getTime()));
                    break;
                }
                case 10: {
                    setValue(dateFormatD.parse(text));
                    break;
                }
                case 5: {
                    setValue(new Time(dateFormatTm.parse(text).getTime()));
                    break;
                }
                default:
                    break;
            }

        } catch (Exception ex) {
            System.out.println("err Date time:" + ex);
            setValue(null);
        }
    }
}
