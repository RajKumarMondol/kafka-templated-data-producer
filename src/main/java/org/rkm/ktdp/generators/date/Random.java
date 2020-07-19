package org.rkm.ktdp.generators.date;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
public class Random extends DateGenerator {
    public Random(@JsonProperty(value = "format") String format) {
        super(format);
    }
}
