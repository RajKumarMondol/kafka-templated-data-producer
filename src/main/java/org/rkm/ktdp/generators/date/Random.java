package org.rkm.ktdp.generators.date;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;

import static org.rkm.ktdp.helpers.TimeHelpers.getTimeInDays;

@EqualsAndHashCode(callSuper = true)
public class Random extends DateGenerator {
    private final LocalDate minimumDate;
    private final LocalDate maximumDate;
    private final int rangeInSeconds;

    public Random(@JsonProperty(value = "format") String format,
                  @JsonProperty(value = "minimum") String minimum,
                  @JsonProperty(value = "maximum") String maximum) {
        super(format);
        this.minimumDate = LocalDate.parse(minimum, this.timeFormatter);
        this.maximumDate = LocalDate.parse(maximum, this.timeFormatter);
        this.rangeInSeconds = getTimeInDays(maximumDate) - getTimeInDays(minimumDate);
    }

    @Override
    public String generate() {
        int randomSeconds = (int) (Math.random() * rangeInSeconds);
        return this.minimumDate.plusDays(randomSeconds).format(timeFormatter);
    }
}
