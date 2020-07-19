package org.rkm.ktdp.templates;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import org.rkm.ktdp.generators.time.TimeGenerator;
import org.rkm.ktdp.templates.specification.BaseTemplate;

@EqualsAndHashCode(callSuper = true)
public class TimeTemplate extends BaseTemplate {

    public TimeTemplate(@JsonProperty(value = "name") String name,
                        @JsonProperty(value = "allowMultiple") boolean allowMultiple,
                        @JsonProperty(value = "generator") TimeGenerator generator) {
        super(name, allowMultiple, generator);
    }
}

