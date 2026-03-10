package sample;

import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;
import java.time.DayOfWeek;
import java.util.List;

public class DateRangeUtils {
    
    public static LocalDateTime startOfDay(LocalDateTime dateTime) {
        return dateTime.toLocalDate().atStartOfDay();
    }
    
    public static LocalDateTime endOfDay(LocalDateTime dateTime) {
        return dateTime.toLocalDate().atTime(23, 59, 59, 999999999);
    }
    
    public static LocalDateTime[] getFullYearRange(LocalDateTime referenceDate) {
        LocalDateTime start = startOfDay(referenceDate.with(TemporalAdjusters.firstDayOfYear()));
        LocalDateTime end = endOfDay(referenceDate.with(TemporalAdjusters.lastDayOfYear()));
        return new LocalDateTime[]{start, end};
    }
    
    public static LocalDateTime[] getFullHalfYearRange(LocalDateTime referenceDate) {
        int month = referenceDate.getMonthValue();
        LocalDateTime start;
        LocalDateTime end;
        
        if (month <= 6) {
            start = startOfDay(referenceDate.withMonth(1).withDayOfMonth(1));
            end = endOfDay(referenceDate.withMonth(6).with(TemporalAdjusters.lastDayOfMonth()));
        } else {
            start = startOfDay(referenceDate.withMonth(7).withDayOfMonth(1));
            end = endOfDay(referenceDate.withMonth(12).with(TemporalAdjusters.lastDayOfMonth()));
        }
        
        return new LocalDateTime[]{start, end};
    }
    
    public static LocalDateTime[] getFullQuarterRange(LocalDateTime referenceDate) {
        int month = referenceDate.getMonthValue();
        int quarterStartMonth = ((month - 1) / 3) * 3 + 1;
        
        LocalDateTime start = startOfDay(referenceDate.withMonth(quarterStartMonth).withDayOfMonth(1));
        LocalDateTime end = endOfDay(referenceDate.withMonth(quarterStartMonth + 2).with(TemporalAdjusters.lastDayOfMonth()));
        
        return new LocalDateTime[]{start, end};
    }
    
    public static LocalDateTime[] getFullMonthRange(LocalDateTime referenceDate) {
        LocalDateTime start = startOfDay(referenceDate.with(TemporalAdjusters.firstDayOfMonth()));
        LocalDateTime end = endOfDay(referenceDate.with(TemporalAdjusters.lastDayOfMonth()));
        return new LocalDateTime[]{start, end};
    }
    
    public static LocalDateTime[] getFullWeekRange(LocalDateTime referenceDate) {
        LocalDateTime start = startOfDay(referenceDate.with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY)));
        LocalDateTime end = endOfDay(referenceDate.with(TemporalAdjusters.nextOrSame(DayOfWeek.SUNDAY)));
        return new LocalDateTime[]{start, end};
    }
    
    public static String formatRange(LocalDateTime start, LocalDateTime end) {
        return start.toString() + " - " + end.toString();
    }
    
    public static List<String> singleRange(LocalDateTime[] range) {
        return List.of(formatRange(range[0], range[1]));
    }
}

