package org.rkm.ktdp.generators.datetime;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
public class Current extends DatetimeGenerator {
    public Current(@JsonProperty(value = "format") String format) {
        super(format);
    }
}
