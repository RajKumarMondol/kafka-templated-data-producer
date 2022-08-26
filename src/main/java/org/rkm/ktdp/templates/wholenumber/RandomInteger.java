package org.rkm.ktdp.templates.wholenumber;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
public class RandomInteger extends IntegerTemplate {
    private final int minimum;
    private final int range;

    public RandomInteger(@JsonProperty(value = "name") String name,
                         @JsonProperty(value = "format") String format,
                         @JsonProperty(value = "minimum") int minimum,
                         @JsonProperty(value = "maximum") int maximum) {
        super(name, format);
        this.minimum = minimum;
        this.range = maximum - minimum + 1;
    }

    @Override
    public String generate() {
        return String.format(format, (int) (Math.random() * range) + minimum);
    }
}
