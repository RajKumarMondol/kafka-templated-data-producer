package org.rkm.ktdp.templates.time;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.time.LocalTime;
import java.time.ZoneId;

@EqualsAndHashCode(callSuper = true)
@ToString
public final class CurrentTime extends TimeTemplate {
    private final ZoneId zoneId;

    public CurrentTime(@JsonProperty(value = "name") String name,
                       @JsonProperty(value = "format") String format,
                       @JsonProperty(value = "zoneId") String zoneId) {
        super(name,format);
        this.zoneId = ZoneId.of(zoneId);
    }

    @Override
    public String generate() {
        return LocalTime.now(zoneId).format(timeFormatter);
    }
}
