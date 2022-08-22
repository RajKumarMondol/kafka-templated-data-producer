package org.rkm.ktdp.generators.time;

import org.junit.jupiter.api.Test;

import java.time.LocalTime;
import java.time.ZoneId;

import static org.assertj.core.api.Assertions.assertThat;


class CurrentTest {

    @Test
    void shouldReturnCurrentTimestamp() {
        Current current = new Current("HH:mm:ss", "UTC");
        assertThat(LocalTime.now(ZoneId.of("UTC")).toString())
                .contains(current.generate());

    }
}