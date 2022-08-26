package org.rkm.ktdp.templates.number;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
public class DoubleSequence extends DoubleTemplate {
    private double currentValue;
    private final double increment;

    public DoubleSequence(@JsonProperty(value = "name") String name,
                          @JsonProperty(value = "format") String format,
                          @JsonProperty(value = "startValue") double startValue,
                          @JsonProperty(value = "increment") double increment) {
        super(name,format);
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