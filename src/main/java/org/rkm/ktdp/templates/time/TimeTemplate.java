package org.rkm.ktdp.templates.time;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.EqualsAndHashCode;
import org.rkm.ktdp.templates.specification.FormattedTemplate;

import java.time.format.DateTimeFormatter;

@EqualsAndHashCode(callSuper = true)
public abstract class TimeTemplate extends FormattedTemplate {
    protected final DateTimeFormatter timeFormatter;

    protected TimeTemplate(@JsonProperty(value = "name") String name,
                           @JsonProperty(value = "format") String format) {
        super(name, format);
        this.timeFormatter = DateTimeFormatter.ofPattern(format);
    }
}
