package sample;

import java.time.LocalDateTime;
import java.util.List;

public interface DateRangeHandler {
    List<String> getDateRange(
            LocalDateTime referenceDate,
            DateRangeProvider.DateRangeKey key,
            TimeAggregation timeAggregation);
}