package org.rkm.ktdp.generators.time;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.EqualsAndHashCode;
import org.rkm.ktdp.generators.specification.FormattedGenerator;

import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = Random.class),
        @JsonSubTypes.Type(value = Sequence.class),
        @JsonSubTypes.Type(value = Current.class),
})
@EqualsAndHashCode(callSuper = true)
public abstract class TimeGenerator extends FormattedGenerator {
    protected final DateTimeFormatter timeFormatter;

    protected TimeGenerator(@JsonProperty(value = "format") String format) {
        super(format);
        this.timeFormatter = DateTimeFormatter.ofPattern(format);
    }
}
