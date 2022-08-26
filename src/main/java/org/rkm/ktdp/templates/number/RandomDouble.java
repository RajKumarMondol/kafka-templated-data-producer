package org.rkm.ktdp.templates.number;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
public class RandomDouble extends DoubleTemplate {
    private final double minimum;
    private final double range;

    public RandomDouble(@JsonProperty(value = "name") String name,
                        @JsonProperty(value = "format") String format,
                        @JsonProperty(value = "minimum") double minimum,
                        @JsonProperty(value = "maximum") double maximum) {
        super(name,format);
        this.minimum = minimum;
        this.range = maximum - minimum;
    }

    @Override
    public String generate() {
        return String.format(format, Math.random() * range + minimum);
    }
}