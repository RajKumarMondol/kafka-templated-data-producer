package org.rkm.ktdp.generators.wholenumber;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
public class Sequence extends IntegerGenerator {
    public Sequence(@JsonProperty(value = "format") String format) {
        super(format);
    }
}