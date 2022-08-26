package org.rkm.ktdp.templates.datetime;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = true)
public final class CurrentDateTime extends DatetimeTemplate {
    public CurrentDateTime(@JsonProperty(value = "name") String name,
                           @JsonProperty(value = "format") String format,
                           @JsonProperty(value = "zoneId") String zoneId) {
        super(name,format,zoneId);
    }

    @Override
    public String generate() {
        return LocalDateTime.now(zoneId).format(dateTimeFormatter);
    }
}

