package org.rkm.ktdp.generators.datetime;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.EqualsAndHashCode;
import org.rkm.ktdp.generators.specification.FormattedGenerator;

import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = Current.class),
        @JsonSubTypes.Type(value = Random.class),
        @JsonSubTypes.Type(value = Sequence.class),
})
@EqualsAndHashCode(callSuper = true)
public abstract class DatetimeGenerator extends FormattedGenerator {
    protected final DateTimeFormatter timeFormatter;

    protected DatetimeGenerator(@JsonProperty(value = "format") String format) {
        super(format);
        this.timeFormatter = new DateTimeFormatterBuilder()
                .appendPattern(format)
                .parseLenient()
                .toFormatter();
    }
}
