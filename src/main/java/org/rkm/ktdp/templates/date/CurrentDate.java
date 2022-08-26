package org.rkm.ktdp.templates.date;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;
import java.time.ZoneId;

@EqualsAndHashCode(callSuper = true)
public class CurrentDate extends DateTemplate {
    private final ZoneId zoneId;

    public CurrentDate(@JsonProperty(value = "name") String name,
                       @JsonProperty(value = "format") String format,
                       @JsonProperty(value = "zoneId") String zoneId) {
        super(name,format);
        this.zoneId = ZoneId.of(zoneId);
    }

    @Override
    public String generate() {
        return LocalDate.now(zoneId).format(timeFormatter);
    }

}
