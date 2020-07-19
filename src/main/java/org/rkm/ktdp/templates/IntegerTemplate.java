package org.rkm.ktdp.templates;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import org.rkm.ktdp.generators.wholenumber.IntegerGenerator;
import org.rkm.ktdp.templates.specification.BaseTemplate;

@EqualsAndHashCode(callSuper = true)
public class IntegerTemplate extends BaseTemplate {

    public IntegerTemplate(@JsonProperty(value = "name") String name,
                           @JsonProperty(value = "allowMultiple") boolean allowMultiple,
                           @JsonProperty(value = "generator") IntegerGenerator generator) {
        super(name, allowMultiple, generator);
    }
}
