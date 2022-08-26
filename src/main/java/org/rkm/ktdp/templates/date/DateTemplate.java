package org.rkm.ktdp.templates.date;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import org.rkm.ktdp.templates.specification.FormattedTemplate;

import java.time.format.DateTimeFormatter;

@EqualsAndHashCode(callSuper = true)
public abstract class DateTemplate extends FormattedTemplate {
    protected final DateTimeFormatter timeFormatter;

    protected DateTemplate(@JsonProperty(value = "name") String name,
                           @JsonProperty(value = "format") String format) {
        super(name, format);
        this.timeFormatter = DateTimeFormatter.ofPattern(format);
    }
}
