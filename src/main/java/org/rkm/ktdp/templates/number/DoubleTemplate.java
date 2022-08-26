package org.rkm.ktdp.templates.number;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.EqualsAndHashCode;
import org.rkm.ktdp.templates.specification.FormattedTemplate;

@EqualsAndHashCode(callSuper = true)
public abstract class DoubleTemplate extends FormattedTemplate {
    protected DoubleTemplate(@JsonProperty(value = "name") String name,
                             @JsonProperty(value = "format") String format) {
        super(name,format);
    }
}
