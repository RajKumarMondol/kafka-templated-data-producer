package org.rkm.ktdp.generators.time;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.time.LocalTime;

@EqualsAndHashCode(callSuper = true)
@ToString
public final class Sequence extends TimeGenerator {
    private LocalTime currentValue;
    private final int incrementInSec;

    public Sequence(@JsonProperty(value = "format") String format,
                    @JsonProperty(value = "startTime") String startTime,
                    @JsonProperty(value = "incrementInSec") int incrementInSec) {
        super(format);
        this.currentValue = LocalTime.parse(startTime, this.timeFormatter);
        this.incrementInSec = incrementInSec;
    }

    @Override
    public String generate() {
        String result = currentValue.format(timeFormatter);
        currentValue = currentValue.plusSeconds(incrementInSec);
        return result;
    }
}