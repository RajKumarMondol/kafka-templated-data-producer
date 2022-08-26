package org.rkm.ktdp.templates.characters;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import org.rkm.ktdp.templates.specification.BaseTemplate;

@EqualsAndHashCode(callSuper = true)
public abstract class StringTemplate extends BaseTemplate {
    public StringTemplate(@JsonProperty(value = "name") String name) {
        super(name);
    }
}
