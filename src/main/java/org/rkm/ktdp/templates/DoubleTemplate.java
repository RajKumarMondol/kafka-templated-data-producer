package org.rkm.ktdp.templates;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import org.rkm.ktdp.generators.number.DoubleGenerator;
import org.rkm.ktdp.templates.specification.BaseTemplate;

@EqualsAndHashCode(callSuper = true)
public class DoubleTemplate extends BaseTemplate {

    public DoubleTemplate(@JsonProperty(value = "name") String name,
                          @JsonProperty(value = "allowMultiple") boolean allowMultiple,
                          @JsonProperty(value = "generator") DoubleGenerator generator) {
        super(name, allowMultiple, generator);
    }
}
