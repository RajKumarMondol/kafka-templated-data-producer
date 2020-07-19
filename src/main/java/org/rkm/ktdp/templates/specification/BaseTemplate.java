package org.rkm.ktdp.templates.specification;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.rkm.ktdp.generators.specification.BaseGenerator;
import org.rkm.ktdp.templates.DateTemplate;
import org.rkm.ktdp.templates.DatetimeTemplate;
import org.rkm.ktdp.templates.DoubleTemplate;
import org.rkm.ktdp.templates.FromSourceTemplate;
import org.rkm.ktdp.templates.IntegerTemplate;
import org.rkm.ktdp.templates.StringTemplate;
import org.rkm.ktdp.templates.TimeTemplate;
import org.rkm.ktdp.templates.UUIDTemplate;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = DateTemplate.class),
        @JsonSubTypes.Type(value = DatetimeTemplate.class),
        @JsonSubTypes.Type(value = DoubleTemplate.class),
        @JsonSubTypes.Type(value = FromSourceTemplate.class),
        @JsonSubTypes.Type(value = IntegerTemplate.class),
        @JsonSubTypes.Type(value = TimeTemplate.class),
        @JsonSubTypes.Type(value = StringTemplate.class),
        @JsonSubTypes.Type(value = UUIDTemplate.class),
})
@EqualsAndHashCode
public abstract class BaseTemplate {
    protected final BaseGenerator generator;
    @Getter
    private final String name;
    @Getter
    private final boolean allowMultiple;

    protected BaseTemplate(@JsonProperty(value = "name") String name,
                           @JsonProperty(value = "allowMultiple") boolean allowMultiple,
                           @JsonProperty(value = "generator") BaseGenerator generator) {
        this.name = name;
        this.allowMultiple = allowMultiple;
        this.generator = generator;
    }

    public String generate() {
        return "unknown";
    }
}

