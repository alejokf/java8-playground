package timeapi;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.OffsetDateTime;
import java.time.Period;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;

public class TimeAPIMain {

    public static void main(String[] args) {
        localDate();
        localTime();
        localDateTime();
        zoneId();
        period();
        duration();
    }

    private static void localDate() {
        System.out.println("Working with LocalDate");
        System.out.println("----------------------");

        // Building LocaleDate
        LocalDate now = LocalDate.now();
        LocalDate start = LocalDate.of(2021, 1, 26);
        LocalDate end = LocalDate.parse("2021-01-30");

        // LocalDate is immutable
        LocalDate tomorrow = now.plusDays(1);
        System.out.println(String.format("Now: %s", now));
        System.out.println(String.format("Tomorrow: %s", tomorrow));

        // Calculate days between two dates
        System.out.println(String.format("Days between start and end date: %s", start.until(end, ChronoUnit.DAYS)));
        System.out.println(String.format("Days between start and end date: %s", ChronoUnit.DAYS.between(start, end)));

        // Days of week, month and year, is leap year
        System.out.println(String.format("Day of Week: %s", now.getDayOfWeek()));
        System.out.println(String.format("Day of Month: %s", now.getDayOfMonth()));
        System.out.println(String.format("Day of Year: %s", now.getDayOfYear()));
        System.out.println(String.format("Is leap year: %s", now.isLeapYear()));

        // Check if it is after or before
        System.out.println(String.format("Is before: %s", start.isBefore(end)));
        System.out.println(String.format("Is after: %s", start.isAfter(end)));

        // Formatting
        System.out.println(String.format("LocalDate in ISO format: %s", now.format(DateTimeFormatter.ISO_LOCAL_DATE)));
        System.out.println(String.format("LocalDate in custom format: %s", now.format(DateTimeFormatter.ofPattern("yyyy.MM.dd"))));

        System.out.println();
    }

    private static void localTime() {
        System.out.println("Working with LocalTime");
        System.out.println("----------------------");

        // Building LocalTime
        LocalTime now = LocalTime.now();
        LocalTime start = LocalTime.of(10, 30);
        LocalTime end = LocalTime.parse("11:15");

        // Calculate minutes between two dates
        System.out.println(String.format("Minutes between start and end date: %s", start.until(end, ChronoUnit.MINUTES)));
        System.out.println(String.format("Minutes between start and end date: %s", ChronoUnit.MINUTES.between(start, end)));

        // LocalTime is immutable
        LocalTime later = now.plusMinutes(5);
        LocalTime muchLater = now.plus(2, ChronoUnit.HOURS);
        System.out.println(String.format("Now: %s", now));
        System.out.println(String.format("5 minutes later: %s", later));
        System.out.println(String.format("2 hours later: %s", muchLater));

        // Nanosecond, Second, Minute and Hour
        System.out.println(String.format("Nanosecond: %s", now.getNano()));
        System.out.println(String.format("Second: %s", now.getSecond()));
        System.out.println(String.format("Minute: %s", now.getMinute()));
        System.out.println(String.format("Hour: %s", now.getHour()));

        // Check if it is after or before
        System.out.println(String.format("Is before: %s", start.isBefore(end)));
        System.out.println(String.format("Is after: %s", start.isAfter(end)));

        // Formatting
        System.out.println(String.format("LocalTime in ISO format: %s", now.format(DateTimeFormatter.ISO_LOCAL_TIME)));
        System.out.println(String.format("LocalTime in custom format: %s", now.format(DateTimeFormatter.ofPattern("HH::mm::ss"))));


        System.out.println();
    }

    private static void localDateTime() {
        System.out.println("Working with LocalDateTime");
        System.out.println("----------------------");

        // Building from Date and Calendar
        Calendar calendar = Calendar.getInstance();
        LocalDateTime fromCalendar = LocalDateTime.ofInstant(calendar.toInstant(), ZoneId.systemDefault());
        Date date = calendar.getTime();
        LocalDateTime fromDate = LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
        System.out.println(String.format("fromCalendar and fromDate should be equal: %s", fromCalendar.isEqual(fromDate)));

        // Building LocalDateTime
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime start = LocalDateTime.of(2021, Month.JANUARY, 20, 1, 26, 10, 30);
        LocalDateTime end = LocalDateTime.parse("2021-01-26T11:45:00");

        // Calculate minutes between two dates
        System.out.println(String.format("Minutes between start and end date: %s", start.until(end, ChronoUnit.MINUTES)));
        System.out.println(String.format("Minutes between start and end date: %s", ChronoUnit.MINUTES.between(start, end)));

        // LocalDateTime is immutable
        LocalDateTime tomorrow = now.plus(1, ChronoUnit.DAYS).plus(2, ChronoUnit.HOURS).plus(5,ChronoUnit.MINUTES);
        System.out.println(String.format("Now: %s", now));
        System.out.println(String.format("1 day, 2 hours and 5 minutes later: %s", tomorrow));

        // Days of week, month and year, Nanosecond, Second, Minute and Hour
        System.out.println(String.format("Day of Week: %s", now.getDayOfWeek()));
        System.out.println(String.format("Day of Month: %s", now.getDayOfMonth()));
        System.out.println(String.format("Day of Year: %s", now.getDayOfYear()));
        System.out.println(String.format("Nanosecond: %s", now.getNano()));
        System.out.println(String.format("Second: %s", now.getSecond()));
        System.out.println(String.format("Minute: %s", now.getMinute()));
        System.out.println(String.format("Hour: %s", now.getHour()));

        // Formatting
        System.out.println(String.format("LocalDate in ISO format: %s", now.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME)));
        System.out.println(String.format("LocalDate in custom format: %s", now.format(DateTimeFormatter.ofPattern("yyyy.MM.dd HH::mm::ss"))));

        System.out.println();
    }

    private static void zoneId() {
        System.out.println("Working with ZoneId");
        System.out.println("----------------------");

        // Building a ZoneId
        ZoneId zoneIdBerlin = ZoneId.of("Europe/Berlin");
        ZoneId zoneIdBogota = ZoneId.of("America/Bogota");

        // Get all Zone Ids
        ZoneId.getAvailableZoneIds().stream()
                .filter(zoneId -> zoneId.equals(zoneIdBerlin) || zoneId.equals(zoneIdBogota))
                .forEach(System.out::println);

        // Calculate time in a different timezone
        LocalDateTime now = LocalDateTime.now(zoneIdBerlin);
        ZonedDateTime nowInBogota = now.atZone(zoneIdBogota);
        System.out.println(String.format("Current time in Berlin: %s", now));
        System.out.println(String.format("Current time in Bogota: %s", nowInBogota));

        ZoneOffset offset = ZoneOffset.of("-05:00");
        OffsetDateTime offSetByTwo = OffsetDateTime.of(now, offset);
        System.out.println(String.format("Current time with -5h offset: %s", offSetByTwo));

        System.out.println();
    }

    private static void period() {
        System.out.println("Working with Period");
        System.out.println("----------------------");

        // Using and creating a Period
        LocalDate start = LocalDate.now();
        LocalDate end = start.plus(Period.ofWeeks(10));
        Period period = Period.between(start, end);

        // Years, Months, Days
        System.out.println(String.format("Years: %s", period.getYears()));
        System.out.println(String.format("Months: %s", period.getMonths()));
        System.out.println(String.format("Days: %s", period.getDays()));

        System.out.println();
    }

    private static void duration() {
        System.out.println("Working with Duration");
        System.out.println("----------------------");

        // Using and creating a Duration
        LocalTime start = LocalTime.of(10, 30);
        LocalTime end = start.plus(Duration.ofMinutes(45));
        Duration duration = Duration.between(start, end);

        // Seconds, Nanos
        System.out.println(String.format("Seconds: %s", duration.getSeconds()));
        System.out.println(String.format("Nanos: %s", duration.getSeconds()));

        System.out.println();
    }


}
