package sample;

import java.time.LocalDateTime;
import java.util.List;

public class ToDateRangeHandler implements DateRangeHandler {
    
    @Override
    public List<String> getDateRange(
            LocalDateTime referenceDate, 
            DateRangeProvider.DateRangeKey key, 
            TimeAggregation timeAggregation) {
        // Placeholder implementation for ToDate ranges
        return List.of("ToDate range for " + key.name());
    }
}

