package org.rkm.ktdp.generators.date;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.EqualsAndHashCode;
import org.rkm.ktdp.generators.specification.FormattedGenerator;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = Random.class),
        @JsonSubTypes.Type(value = Sequence.class),
})
@EqualsAndHashCode(callSuper = true)
public abstract class DateGenerator extends FormattedGenerator {
    protected DateGenerator(@JsonProperty(value = "format") String format) {
        super(format);
    }
}
