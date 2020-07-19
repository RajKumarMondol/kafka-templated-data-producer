package org.rkm.ktdp.generators.time;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
public class Random extends TimeGenerator {
    public Random(@JsonProperty(value = "format") String format) {
        super(format);
    }
}