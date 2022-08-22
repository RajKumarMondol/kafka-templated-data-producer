package org.rkm.ktdp.templates;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import org.rkm.ktdp.generators.characters.StringGenerator;
import org.rkm.ktdp.templates.specification.BaseTemplate;

@EqualsAndHashCode(callSuper = true)
public class StringTemplate extends BaseTemplate {

    public StringTemplate(@JsonProperty(value = "name") String name,
                          @JsonProperty(value = "allowMultiple") boolean allowMultiple,
                          @JsonProperty(value = "generator") StringGenerator generator) {
        super(name, allowMultiple, generator);
    }
}