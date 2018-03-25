package com.example.lingxiao.tasktodo.Utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * dateString to date/ date to dateString
 */

public class DateUtils {

    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy MM dd HH:mm", Locale.getDefault());

    public static Date stringToDate(String dateString){
        try {
            return sdf.parse(dateString);
        } catch (ParseException e) {
            return Calendar.getInstance().getTime();
        }

    }

    public static String dateToString(Date date){
        return sdf.format(date);
    }
}
