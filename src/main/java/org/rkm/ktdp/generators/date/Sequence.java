package org.rkm.ktdp.generators.date;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
public class Sequence extends DateGenerator {
    public Sequence(@JsonProperty(value = "format") String format) {
        super(format);
    }
}