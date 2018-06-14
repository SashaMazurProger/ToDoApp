package sample.utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DateUtils {

    private static final String DATE_PATTERN = "dd.MM.yyyy HH:mm";
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern(DATE_PATTERN);

    /*
     * Return date string of a date
     *
     * @param localDate - date which will be return as string
     * */
    public static String format(LocalDateTime localDate) {
        if (localDate == null) {
            return null;
        }
        return localDate.format(DATE_TIME_FORMATTER);
    }

    public static LocalDateTime parse(String localDateString) {
        if (localDateString == null || localDateString.isEmpty()) {
            return null;
        }

        try {
            return LocalDateTime.parse(localDateString,DATE_TIME_FORMATTER);
        } catch (DateTimeParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static boolean isCorrectStringDate(String localDateString){
        return (DateUtils.parse(localDateString)!=null);
    }
}
