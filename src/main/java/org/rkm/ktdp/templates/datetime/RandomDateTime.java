package org.rkm.ktdp.templates.datetime;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = true)
public class RandomDateTime extends DatetimeTemplate {
    private final LocalDateTime minimumDateTime;
    private final LocalDateTime maximumDateTime;
    private final long rangeInSeconds;

    public RandomDateTime(@JsonProperty(value = "name") String name,
                          @JsonProperty(value = "format") String format,
                          @JsonProperty(value = "zoneId") String zoneId,
                          @JsonProperty(value = "minimum") String minimum,
                          @JsonProperty(value = "maximum") String maximum) {
        super(name,format, zoneId);
        this.minimumDateTime = LocalDateTime.from(this.dateTimeFormatter.parse(minimum));
        this.maximumDateTime = LocalDateTime.from(this.dateTimeFormatter.parse(maximum));
        this.rangeInSeconds = 10;//getTimeInSeconds(maximumDateTime) - getTimeInSeconds(minimumDateTime);
    }


    @Override
    public String generate() {
        long randomSeconds = (long) (Math.random() * rangeInSeconds);
//        return this.minimumDateTime.plusSeconds(randomSeconds).format(timeFormatter);
        return dateTimeFormatter.format(this.minimumDateTime.plusSeconds(randomSeconds));
    }
}
