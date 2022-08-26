package org.rkm.ktdp.templates.wholenumber;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
public class IntegerSequence extends IntegerTemplate {
    private int currentValue;
    private final int increment;

    public IntegerSequence(@JsonProperty(value = "name") String name,
                           @JsonProperty(value = "format") String format,
                           @JsonProperty(value = "startValue") int startValue,
                           @JsonProperty(value = "increment") int increment) {
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