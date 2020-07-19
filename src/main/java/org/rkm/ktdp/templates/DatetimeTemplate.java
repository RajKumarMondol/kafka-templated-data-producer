package org.rkm.ktdp.templates;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import org.rkm.ktdp.generators.datetime.DatetimeGenerator;
import org.rkm.ktdp.templates.specification.BaseTemplate;

@EqualsAndHashCode(callSuper = true)
public class DatetimeTemplate extends BaseTemplate {

    public DatetimeTemplate(@JsonProperty(value = "name") String name,
                            @JsonProperty(value = "allowMultiple") boolean allowMultiple,
                            @JsonProperty(value = "generator") DatetimeGenerator generator) {
        super(name, allowMultiple, generator);
    }
}
