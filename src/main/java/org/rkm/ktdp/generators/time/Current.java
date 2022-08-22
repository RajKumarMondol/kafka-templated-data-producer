package org.rkm.ktdp.generators.time;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
public class Current extends TimeGenerator {
    public Current(@JsonProperty(value = "format") String format) {
        super(format);
    }
}
