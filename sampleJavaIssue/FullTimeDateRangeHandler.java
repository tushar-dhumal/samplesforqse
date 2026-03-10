package sample;

import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;
import java.util.List;

public class FullTimeDateRangeHandler implements DateRangeHandler {
    
    
    
    @Override
    public List<String> getDateRange(
            LocalDateTime referenceDate, 
            DateRangeProvider.DateRangeKey key, 
            TimeAggregation timeAggregation) {
        return switch (key) {
            case CURRENT_YEAR_FULL_TIME -> DateRangeUtils.singleRange(DateRangeUtils.getFullYearRange(referenceDate));
            case CURRENT_HALF_YEAR_FULL_TIME -> DateRangeUtils.singleRange(DateRangeUtils.getFullHalfYearRange(referenceDate));
            case CURRENT_QUARTER_FULL_TIME -> DateRangeUtils.singleRange(DateRangeUtils.getFullQuarterRange(referenceDate));
            case CURRENT_MONTH_FULL_TIME -> DateRangeUtils.singleRange(DateRangeUtils.getFullMonthRange(referenceDate));
            case CURRENT_WEEK_FULL_TIME -> {
                if (timeAggregation == TimeAggregation.MONTH) {
                    yield DateRangeUtils.singleRange(DateRangeUtils.getFullMonthRange(referenceDate));
                } else {
                    yield DateRangeUtils.singleRange(DateRangeUtils.getFullWeekRange(referenceDate));
                }
            }
            case PREVIOUS_YEAR_FULL_TIME -> DateRangeUtils.singleRange(DateRangeUtils.getFullYearRange(referenceDate.minusYears(1)));
            case PREVIOUS_HALF_YEAR_FULL_TIME -> DateRangeUtils.singleRange(DateRangeUtils.getFullHalfYearRange(referenceDate.minusMonths(6)));
            case PREVIOUS_QUARTER_FULL_TIME -> DateRangeUtils.singleRange(DateRangeUtils.getFullQuarterRange(referenceDate.minusMonths(3)));
            case PREVIOUS_MONTH_FULL_TIME -> DateRangeUtils.singleRange(DateRangeUtils.getFullMonthRange(referenceDate.minusMonths(1)));
            case PREVIOUS_WEEK_FULL_TIME -> {
                if (timeAggregation == TimeAggregation.MONTH) {
                    yield DateRangeUtils.singleRange(DateRangeUtils.getFullMonthRange(referenceDate.minusWeeks(1)));
                } else if (timeAggregation == TimeAggregation.WEEK) {
                    yield DateRangeUtils.singleRange(DateRangeUtils.getFullWeekRange(referenceDate.minusWeeks(1)));
                } else {
                    yield DateRangeUtils.singleRange(DateRangeUtils.getFullWeekRange(referenceDate.minusWeeks(1)));
                }
            }
            case NEXT_YEAR_FULL_TIME -> DateRangeUtils.singleRange(DateRangeUtils.getFullYearRange(referenceDate.plusYears(1)));
            case NEXT_HALF_YEAR_FULL_TIME -> DateRangeUtils.singleRange(DateRangeUtils.getFullHalfYearRange(referenceDate.plusMonths(6)));
            case NEXT_MONTH_FULL_TIME -> DateRangeUtils.singleRange(DateRangeUtils.getFullMonthRange(referenceDate.plusMonths(1)));
            case NEXT_WEEK_FULL_TIME -> {
                if (timeAggregation == TimeAggregation.MONTH) {
                    yield DateRangeUtils.singleRange(DateRangeUtils.getFullMonthRange(referenceDate.plusWeeks(1)));
                } else {
                    yield DateRangeUtils.singleRange(DateRangeUtils.getFullWeekRange(referenceDate.plusWeeks(1)));
                }
            }
            case PREVIOUS_AND_CURRENT_YEAR_FULL_TIME -> {
                LocalDateTime start = DateRangeUtils.startOfDay(referenceDate.minusYears(1).with(TemporalAdjusters.firstDayOfYear()));
                LocalDateTime end = DateRangeUtils.endOfDay(referenceDate.with(TemporalAdjusters.lastDayOfYear()));
                yield List.of(DateRangeUtils.formatRange(start, end));
            }
            case PREVIOUS_AND_CURRENT_HALF_YEAR_FULL_TIME -> List.of(
                    DateRangeUtils.formatRange(
                            DateRangeUtils.getFullHalfYearRange(referenceDate.minusMonths(6))[0],
                            DateRangeUtils.getFullHalfYearRange(referenceDate.minusMonths(6))[1]),
                    DateRangeUtils.formatRange(DateRangeUtils.getFullHalfYearRange(referenceDate)[0], DateRangeUtils.getFullHalfYearRange(referenceDate)[1]));
            case PREVIOUS_AND_CURRENT_MONTH_FULL_TIME -> {
                LocalDateTime start = DateRangeUtils.startOfDay(referenceDate.minusMonths(1).with(TemporalAdjusters.firstDayOfMonth()));
                LocalDateTime end = DateRangeUtils.endOfDay(referenceDate.with(TemporalAdjusters.lastDayOfMonth()));
                yield List.of(DateRangeUtils.formatRange(start, end));
            }
            case PREVIOUS_AND_CURRENT_WEEK_FULL_TIME -> List.of(
                    DateRangeUtils.formatRange(
                            DateRangeUtils.getFullWeekRange(referenceDate.minusWeeks(1))[0],
                            DateRangeUtils.getFullWeekRange(referenceDate.minusWeeks(1))[1]),
                    DateRangeUtils.formatRange(DateRangeUtils.getFullWeekRange(referenceDate)[0], DateRangeUtils.getFullWeekRange(referenceDate)[1]));
            case CURRENT_AND_NEXT_YEAR_FULL_TIME -> {
                LocalDateTime start = DateRangeUtils.startOfDay(referenceDate.with(TemporalAdjusters.firstDayOfYear()));
                LocalDateTime end = DateRangeUtils.endOfDay(referenceDate.plusYears(1).with(TemporalAdjusters.lastDayOfYear()));
                yield List.of(DateRangeUtils.formatRange(start, end));
            }
            case CURRENT_AND_NEXT_HALF_YEAR_FULL_TIME -> List.of(
                    DateRangeUtils.formatRange(DateRangeUtils.getFullHalfYearRange(referenceDate)[0], DateRangeUtils.getFullHalfYearRange(referenceDate)[1]),
                    DateRangeUtils.formatRange(
                            DateRangeUtils.getFullHalfYearRange(referenceDate.plusMonths(6))[0],
                            DateRangeUtils.getFullHalfYearRange(referenceDate.plusMonths(6))[1]));
            case CURRENT_AND_NEXT_MONTH_FULL_TIME -> {
                LocalDateTime start = DateRangeUtils.startOfDay(referenceDate.with(TemporalAdjusters.firstDayOfMonth()));
                LocalDateTime end = DateRangeUtils.endOfDay(referenceDate.plusMonths(1).with(TemporalAdjusters.lastDayOfMonth()));
                yield List.of(DateRangeUtils.formatRange(start, end));
            }
            case CURRENT_AND_NEXT_WEEK_FULL_TIME -> List.of(
                    DateRangeUtils.formatRange(DateRangeUtils.getFullWeekRange(referenceDate)[0], DateRangeUtils.getFullWeekRange(referenceDate)[1]),
                    DateRangeUtils.formatRange(
                            DateRangeUtils.getFullWeekRange(referenceDate.plusWeeks(1))[0],
                            DateRangeUtils.getFullWeekRange(referenceDate.plusWeeks(1))[1]));
            default -> List.of();
        };
    }
}

