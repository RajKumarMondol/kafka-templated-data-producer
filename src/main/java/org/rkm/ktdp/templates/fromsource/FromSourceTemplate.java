package org.rkm.ktdp.templates.fromsource;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.EqualsAndHashCode;
import org.rkm.ktdp.datasource.BaseSource;
import org.rkm.ktdp.templates.specification.BaseTemplate;

@EqualsAndHashCode(callSuper = true)
public abstract class FromSourceTemplate extends BaseTemplate {
    protected final BaseSource source;

    public FromSourceTemplate(@JsonProperty(value = "name") String name,
                              @JsonProperty(value = "source") BaseSource source) {
        super(name);
        this.source = source;
    }
}
