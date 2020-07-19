package org.rkm.ktdp.generators.number;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
public class Sequence extends DoubleGenerator {
    public Sequence(@JsonProperty(value = "format") String format) {
        super(format);
    }
}