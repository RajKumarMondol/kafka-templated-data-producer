package org.rkm.ktdp.generators.time;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.time.LocalTime;

import static org.rkm.ktdp.helpers.TimeHelpers.getTimeInSeconds;

@EqualsAndHashCode(callSuper = true)
@ToString
public final class Random extends TimeGenerator {
    private final LocalTime minimumTime;
    private final LocalTime maximumTime;
    private final int rangeInSeconds;

    public Random(@JsonProperty(value = "format") String format,
                  @JsonProperty(value = "minimum") String minimum,
                  @JsonProperty(value = "maximum") String maximum) {
        super(format);
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