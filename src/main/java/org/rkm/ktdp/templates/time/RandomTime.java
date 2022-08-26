package org.rkm.ktdp.templates.time;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.time.LocalTime;

import static org.rkm.ktdp.helpers.TimeHelpers.getTimeInSeconds;

@EqualsAndHashCode(callSuper = true)
@ToString
public final class RandomTime extends TimeTemplate {
    private final LocalTime minimumTime;
    private final LocalTime maximumTime;
    private final int rangeInSeconds;

    public RandomTime(@JsonProperty(value = "name") String name,
                      @JsonProperty(value = "format") String format,
                      @JsonProperty(value = "minimum") String minimum,
                      @JsonProperty(value = "maximum") String maximum) {
        super(name,format);
        this.minimumTime = LocalTime.parse(minimum, this.timeFormatter);
        this.maximumTime = LocalTime.parse(maximum, this.timeFormatter);
        this.rangeInSeconds = getTimeInSeconds(maximumTime) - getTimeInSeconds(minimumTime);
    }

    @Override
    public String generate() {
        int randomSeconds = (int) (Math.random() * rangeInSeconds);
        return this.minimumTime.plusSeconds(randomSeconds).format(timeFormatter);
    }
}