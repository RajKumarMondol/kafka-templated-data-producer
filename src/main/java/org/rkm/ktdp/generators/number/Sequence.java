package org.rkm.ktdp.generators.number;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
public class Sequence extends DoubleGenerator {
    private double currentValue;
    private final double increment;

    public Sequence(@JsonProperty(value = "format") String format,
                    @JsonProperty(value = "startValue") double startValue,
                    @JsonProperty(value = "increment") double increment) {
        super(format);
        this.currentValue = startValue;
        this.increment = increment;
    }

    @Override
    public String generate() {
        String result = String.format(format, currentValue);
        currentValue += increment;
        return result;
    }

}