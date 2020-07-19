package org.rkm.ktdp.generators.fromsource;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.EqualsAndHashCode;
import org.rkm.ktdp.datasource.BaseSource;
import org.rkm.ktdp.generators.specification.BaseGenerator;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = Random.class),
        @JsonSubTypes.Type(value = Sequence.class),
})
@EqualsAndHashCode(callSuper = true)
public abstract class FromSourceGenerator extends BaseGenerator {
    public final BaseSource source;

    public FromSourceGenerator(@JsonProperty(value = "source") BaseSource source) {
        this.source = source;
    }
}
