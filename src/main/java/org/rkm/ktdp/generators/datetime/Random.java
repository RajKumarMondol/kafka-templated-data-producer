package org.rkm.ktdp.generators.datetime;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;

import java.time.Instant;

@EqualsAndHashCode(callSuper = true)
public class Random extends DatetimeGenerator {
    private final Instant minimumDateTime;
    private final Instant maximumDateTime;
    private final long rangeInSeconds;

    public Random(@JsonProperty(value = "format") String format,
                  @JsonProperty(value = "minimum") String minimum,
                  @JsonProperty(value = "maximum") String maximum) {
        super(format);
        this.minimumDateTime = this.timeFormatter.parse(minimum, Instant::from);
        this.maximumDateTime = this.timeFormatter.parse(maximum, Instant::from);
        this.rangeInSeconds = 10;//getTimeInSeconds(maximumDateTime) - getTimeInSeconds(minimumDateTime);
    }


    @Override
    public String generate() {
        long randomSeconds = (long) (Math.random() * rangeInSeconds);
//        return this.minimumDateTime.plusSeconds(randomSeconds).format(timeFormatter);
        return timeFormatter.format(this.minimumDateTime.plusSeconds(randomSeconds));
    }
}
