package org.rkm.ktdp.generators.number;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
public class Random extends DoubleGenerator {
    private final double minimum;
    private final double range;

    public Random(@JsonProperty(value = "format") String format,
                  @JsonProperty(value = "minimum") double minimum,
                  @JsonProperty(value = "maximum") double maximum) {
        super(format);
        this.minimum = minimum;
        this.range = maximum - minimum;
    }

    @Override
    public String generate() {
        return String.format(format, Math.random() * range + minimum);
    }
}