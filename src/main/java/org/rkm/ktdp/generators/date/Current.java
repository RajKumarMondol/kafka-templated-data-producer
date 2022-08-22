package org.rkm.ktdp.generators.date;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;
import java.time.ZoneId;

@EqualsAndHashCode(callSuper = true)
public class Current extends DateGenerator {
    private final ZoneId zoneId;

    public Current(@JsonProperty(value = "format") String format,
                   @JsonProperty(value = "zoneId") String zoneId) {
        super(format);
        this.zoneId = ZoneId.of(zoneId);
    }

    @Override
    public String generate() {
        return LocalDate.now(zoneId).format(timeFormatter);
    }

}
