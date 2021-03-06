package org.rkm.ktdp.templates;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import org.rkm.ktdp.generators.date.DateGenerator;
import org.rkm.ktdp.templates.specification.BaseTemplate;

@EqualsAndHashCode(callSuper = true)
public class DateTemplate extends BaseTemplate {

    public DateTemplate(@JsonProperty(value = "name") String name,
                        @JsonProperty(value = "allowMultiple") boolean allowMultiple,
                        @JsonProperty(value = "generator") DateGenerator generator) {
        super(name, allowMultiple, generator);
    }
}
