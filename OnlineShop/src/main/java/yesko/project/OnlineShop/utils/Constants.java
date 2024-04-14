package yesko.project.OnlineShop.utils;

import lombok.Getter;
import lombok.Setter;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

@Getter
@Setter
public class Constants {
    public static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    public static final String START_DATE_FOR_SEMESTER = "22.01.2024";
    public static final String DATE_FORMAT = "dd.MM.yyyy";
    public static final String TIME_FORMAT = "hh:mm a";
    public static final String SEPARATE_BY_COMMA = ",";
    public static final String SEPARATE_BY_POINT = ".";
    public static final Integer SIX_SIZED_SECRET_CODE = 6;
    public static final Integer EIGHT_SIZED_SECRET_CODE = 8;
    public static final Integer ABSENCE_COEFFICIENT = 2;
    public static final Integer TIME_INTERVAL_BETWEEN_SECRET_CODE = 2;
    public static final Integer LESSON_START_TIME = 8;
    public static final Integer LESSON_END_TIME = 20;
    public static final int MONDAY = 1;
    public static final int TUESDAY = 2;
    public static final int WEDNESDAY = 3;
    public static final int THURSDAY = 4;
    public static final int FRIDAY = 5;
    public static final int SATURDAY = 6;
    public static final Boolean USE_LETTERS_IN_SECRET_CODE = true;
    public static final Boolean USE_NUMBERS_IN_SECRET_CODE = true;
}

