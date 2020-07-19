package org.rkm.ktdp.templates.specification;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.rkm.ktdp.generators.specification.BaseGenerator;

@EqualsAndHashCode(callSuper = true)
public abstract class FormattedTemplate extends BaseTemplate {
    @Getter
    private final String format;

    protected FormattedTemplate(@JsonProperty(value = "name") String name,
                                @JsonProperty(value = "allowMultiple") boolean allowMultiple,
                                @JsonProperty(value = "generator") BaseGenerator generator,
                                @JsonProperty(value = "format") String format) {
        super(name, allowMultiple, generator);
        this.format = format;
    }
}

