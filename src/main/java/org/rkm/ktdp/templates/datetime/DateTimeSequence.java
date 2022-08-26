package org.rkm.ktdp.templates.datetime;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
public class DateTimeSequence extends DatetimeTemplate {
    public DateTimeSequence(@JsonProperty(value = "name") String name,
                            @JsonProperty(value = "format") String format,
                            @JsonProperty(value = "zoneId") String zoneId) {
        super(name,format, zoneId);
    }

    @Override
    public String generate() {
        return "yetToBeImplemented";
    }

}
