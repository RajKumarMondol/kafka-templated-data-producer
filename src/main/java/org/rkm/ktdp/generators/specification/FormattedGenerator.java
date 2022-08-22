package org.rkm.ktdp.generators.specification;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;

import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

@EqualsAndHashCode(callSuper = true)
public abstract class FormattedGenerator extends BaseGenerator {
    protected final String format;

    protected FormattedGenerator(@JsonProperty(value = "format") String format) {
        super();
        this.format = format;
    }
}


