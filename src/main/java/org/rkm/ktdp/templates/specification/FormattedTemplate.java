package org.rkm.ktdp.templates.specification;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
public abstract class FormattedTemplate extends BaseTemplate {
    protected final String format;

    protected FormattedTemplate(@JsonProperty(value = "name") String name,
                                @JsonProperty(value = "format") String format) {
        super(name);
        this.format = format;
    }
}


