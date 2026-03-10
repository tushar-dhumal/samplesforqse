package sample;

import java.time.LocalDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class DateRangeProvider {

    public static List<String> getDateRange(
            LocalDateTime referenceDate, DateRangeKey key, TimeAggregation timeAggregation) {
        DateRangeHandler handler = getHandler(key);
        return handler.getDateRange(referenceDate, key, timeAggregation);
    }

    private static DateRangeHandler getHandler(DateRangeKey key) {
        return key.isFullTime() ? new FullTimeDateRangeHandler() : new ToDateRangeHandler();
    }

    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    public enum DateRangeKey {
        // FULL_TIME keys
        CURRENT_YEAR_FULL_TIME(1, true),
        CURRENT_HALF_YEAR_FULL_TIME(2, true),
        CURRENT_QUARTER_FULL_TIME(3, true),
        CURRENT_MONTH_FULL_TIME(4, true),
        CURRENT_WEEK_FULL_TIME(5, true),

        PREVIOUS_YEAR_FULL_TIME(1, true),
        PREVIOUS_HALF_YEAR_FULL_TIME(2, true),
        PREVIOUS_QUARTER_FULL_TIME(3, true),
        PREVIOUS_MONTH_FULL_TIME(4, true),
        PREVIOUS_WEEK_FULL_TIME(5, true),

        NEXT_YEAR_FULL_TIME(1, true),
        NEXT_HALF_YEAR_FULL_TIME(2, true),
        NEXT_MONTH_FULL_TIME(3, true),
        NEXT_WEEK_FULL_TIME(4, true),

        PREVIOUS_AND_CURRENT_YEAR_FULL_TIME(1, true),
        PREVIOUS_AND_CURRENT_HALF_YEAR_FULL_TIME(2, true),
        PREVIOUS_AND_CURRENT_MONTH_FULL_TIME(3, true),
        PREVIOUS_AND_CURRENT_WEEK_FULL_TIME(4, true),

        CURRENT_AND_NEXT_YEAR_FULL_TIME(1, true),
        CURRENT_AND_NEXT_HALF_YEAR_FULL_TIME(2, true),
        CURRENT_AND_NEXT_MONTH_FULL_TIME(3, true),
        CURRENT_AND_NEXT_WEEK_FULL_TIME(4, true),

        // TODATE keys
        CURRENT_YEAR_TODATE(1, false),
        CURRENT_HALF_YEAR_TODATE(2, false),
        CURRENT_QUARTER_TODATE(3, false),
        CURRENT_MONTH_TODATE(4, false),
        CURRENT_WEEK_TODATE(5, false),

        PREVIOUS_YEAR_TODATE(1, false),
        PREVIOUS_HALF_YEAR_TODATE(2, false),
        PREVIOUS_QUARTER_TODATE(3, false),
        PREVIOUS_MONTH_TODATE(4, false),
        PREVIOUS_WEEK_TODATE(5, false),

        NEXT_YEAR_TODATE(1, false),
        NEXT_HALF_YEAR_TODATE(2, false),
        NEXT_QUARTER_TODATE(3, false),
        NEXT_MONTH_TODATE(4, false),
        NEXT_WEEK_TODATE(5, false),
        NEXT_DAY_TODATE(6, false),

        PREVIOUS_AND_CURRENT_YEAR_TODATE(1, false),
        PREVIOUS_AND_CURRENT_HALF_YEAR_TODATE(2, false),
        PREVIOUS_AND_CURRENT_QUARTER_TODATE(3, false),
        PREVIOUS_AND_CURRENT_MONTH_TODATE(4, false),
        PREVIOUS_AND_CURRENT_WEEK_TODATE(5, false),

        CURRENT_AND_NEXT_HALF_YEAR_TODATE(1, false),
        CURRENT_AND_NEXT_YEAR_TODATE(2, false),
        CURRENT_AND_NEXT_MONTH_TODATE(3, false),
        CURRENT_AND_NEXT_QUARTER_TODATE(4, false),
        CURRENT_AND_NEXT_WEEK_TODATE(5, false),
        CURRENT_AND_NEXT_DAY_TODATE(6, false);

        private Integer priority;
        private boolean fullTime;
    }
}
