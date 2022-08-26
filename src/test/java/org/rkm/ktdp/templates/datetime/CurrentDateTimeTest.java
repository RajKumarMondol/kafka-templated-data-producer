package org.rkm.ktdp.templates.datetime;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.ZoneId;

import static org.assertj.core.api.Assertions.assertThat;

class CurrentDateTimeTest {

    @Test
    void shouldReturnCurrentDateTimestampInLocal() {
        CurrentDateTime current = new CurrentDateTime("name","YYYY-MM-dd'T'HH:mm:ss", "UTC");
        assertThat(LocalDateTime.now(ZoneId.of("UTC")).toString())
                .contains(current.generate());
    }
}