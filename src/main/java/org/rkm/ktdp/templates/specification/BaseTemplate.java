package org.rkm.ktdp.templates.specification;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.rkm.ktdp.generators.specification.BaseGenerator;
import org.rkm.ktdp.templates.*;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = DateTemplate.class),
        @JsonSubTypes.Type(value = DatetimeTemplate.class),
        @JsonSubTypes.Type(value = DoubleTemplate.class),
        @JsonSubTypes.Type(value = FromSourceTemplate.class),
        @JsonSubTypes.Type(value = IntegerTemplate.class),
        @JsonSubTypes.Type(value = TimeTemplate.class),
        @JsonSubTypes.Type(value = StringTemplate.class),
})
@EqualsAndHashCode
@ToString
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
        return generator.generate();
    }
}

