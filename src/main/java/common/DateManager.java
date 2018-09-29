package main.java.common;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


/**
 * This class takes care for time conversions.
 */
public class DateManager {
    /**
     * Converts string date to milliseconds.
     * @param date date in sting (dd-M-yyyy hh:mm:ss)
     * @return number of milliseconds since 1.1.1970
     */
    public static long getTimestampFromDate(String date) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-M-yyyy hh:mm:ss");
        try {
            return sdf.parse(date).getTime();
        } catch (ParseException p) {
            System.out.println("Could not parse this date: " + date);
            return -1;
        }
    }

    /**
     * Convertsdate to milliseconds.
     * @param date date
     * @return number of milliseconds since 1.1.1970
     */
    public static long getTimestampFromDate(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.getTimeInMillis();
    }
}
