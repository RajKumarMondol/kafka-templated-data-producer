package org.rkm.ktdp.generators.specification;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@EqualsAndHashCode(callSuper = true)
public abstract class FormattedGenerator extends BaseGenerator {
    @Getter
    private final String format;

    protected FormattedGenerator(@JsonProperty(value = "format") String format) {
        super();
        this.format = format;
    }
}
