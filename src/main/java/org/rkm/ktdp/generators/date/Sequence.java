package org.rkm.ktdp.generators.date;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;

@EqualsAndHashCode(callSuper = true)
public class Sequence extends DateGenerator {
    private LocalDate currentValue;
    private final int incrementInDays;

    public Sequence(@JsonProperty(value = "format") String format,
                    @JsonProperty(value = "startDate") String startDate,
                    @JsonProperty(value = "incrementInDays") int incrementInDays) {
        super(format);
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