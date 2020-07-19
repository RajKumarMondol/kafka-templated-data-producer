package org.rkm.ktdp.generators.time;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
public class Sequence extends TimeGenerator {
    public Sequence(@JsonProperty(value = "format") String format) {
        super(format);
    }
}