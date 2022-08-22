package org.rkm.ktdp.generators.time;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.time.LocalTime;
import java.time.ZoneId;

@EqualsAndHashCode(callSuper = true)
@ToString
public final class Current extends TimeGenerator {
    private final ZoneId zoneId;

    public Current(@JsonProperty(value = "format") String format,
                   @JsonProperty(value = "zoneId") String zoneId) {
        super(format);
        this.zoneId = ZoneId.of(zoneId);
    }

    @Override
    public String generate() {
        return LocalTime.now(zoneId).format(timeFormatter);
    }
}
