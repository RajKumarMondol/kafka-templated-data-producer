package org.rkm.ktdp.generators.datetime;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.ZoneId;

import static org.assertj.core.api.Assertions.assertThat;

class CurrentTest {

    @Test
    void shouldReturnCurrentDateTimestampInLocal() {
        Current current = new Current("YYYY-MM-dd'T'HH:mm:ss", "UTC");
        assertThat(LocalDateTime.now(ZoneId.of("UTC")).toString())
                .contains(current.generate());
    }
}