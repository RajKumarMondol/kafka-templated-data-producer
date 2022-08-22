package org.rkm.ktdp.generators.wholenumber;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
public class Sequence extends IntegerGenerator {
    private int currentValue;
    private final int increment;

    public Sequence(@JsonProperty(value = "format") String format,
                    @JsonProperty(value = "startValue") int startValue,
                    @JsonProperty(value = "increment") int increment) {
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