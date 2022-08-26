package org.rkm.ktdp.templates.date;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;

@EqualsAndHashCode(callSuper = true)
public class DateSequence extends DateTemplate {
    private LocalDate currentValue;
    private final int incrementInDays;

    public DateSequence(@JsonProperty(value = "name") String name,
                        @JsonProperty(value = "format") String format,
                        @JsonProperty(value = "startDate") String startDate,
                        @JsonProperty(value = "incrementInDays") int incrementInDays) {
        super(name,format);
        this.currentValue = LocalDate.parse(startDate, this.timeFormatter);
        this.incrementInDays = incrementInDays;
    }

    @Override
    public String generate() {
        String result = currentValue.format(timeFormatter);
        currentValue = currentValue.plusDays(incrementInDays);
        return result;
    }
}