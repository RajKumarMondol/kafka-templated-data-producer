package org.rkm.ktdp.generators.wholenumber;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
public class Random extends IntegerGenerator {
    private final int minimum;
    private final int range;

    public Random(@JsonProperty(value = "format") String format,
                  @JsonProperty(value = "minimum") int minimum,
                  @JsonProperty(value = "maximum") int maximum) {
        super(format);
        this.minimum = minimum;
        this.range = maximum - minimum + 1;
    }

    @Override
    public String generate() {
        return String.format(format, (int) (Math.random() * range) + minimum);
    }
}
