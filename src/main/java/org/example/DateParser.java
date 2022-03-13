package org.example;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateParser {
    SimpleDateFormat formatDate = new SimpleDateFormat("dd.MM.yyyy");
    SimpleDateFormat formatTime = new SimpleDateFormat("HH:mm");

    public String getDate(Date date) {
        return formatDate.format(date);
    }

    public String getTime(Date date) {
        return formatTime.format(date);
    }
}
