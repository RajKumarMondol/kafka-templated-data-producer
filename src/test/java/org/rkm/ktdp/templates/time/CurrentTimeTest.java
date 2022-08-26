package org.rkm.ktdp.templates.time;

import org.junit.jupiter.api.Test;

import java.time.LocalTime;
import java.time.ZoneId;

import static org.assertj.core.api.Assertions.assertThat;


class CurrentTimeTest {

    @Test
    void shouldReturnCurrentTimestamp() {
        CurrentTime currentTime = new CurrentTime("name","HH:mm:ss", "UTC");
        assertThat(LocalTime.now(ZoneId.of("UTC")).toString())
                .contains(currentTime.generate());

    }
}