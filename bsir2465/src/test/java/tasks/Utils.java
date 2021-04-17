package tasks;

import java.sql.Date;
import java.time.LocalDate;

public class Utils {
    public static Date d(LocalDate localDate) {
        return Date.valueOf(localDate);
    }
}
