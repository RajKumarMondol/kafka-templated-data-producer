package org.rkm.ktdp.templates;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import org.rkm.ktdp.generators.fromsource.FromSourceGenerator;
import org.rkm.ktdp.templates.specification.BaseTemplate;

@EqualsAndHashCode(callSuper = true)
public class FromSourceTemplate extends BaseTemplate {

    public FromSourceTemplate(@JsonProperty(value = "name") String name,
                              @JsonProperty(value = "allowMultiple") boolean allowMultiple,
                              @JsonProperty(value = "generator") FromSourceGenerator generator
    ) {
        super(name, allowMultiple, generator);
    }
}
