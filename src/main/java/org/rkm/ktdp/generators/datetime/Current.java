package org.rkm.ktdp.generators.datetime;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;
import java.time.ZoneId;

@EqualsAndHashCode(callSuper = true)
public final class Current extends DatetimeGenerator {
    private final ZoneId zoneId;
    public Current(@JsonProperty(value = "format") String format,
                   @JsonProperty(value = "zoneId") String zoneId) {
        super(format);
        this.zoneId = ZoneId.of(zoneId);

    }

    @Override
    public String generate() {
        return LocalDateTime.now(zoneId).format(timeFormatter);
    }
}

