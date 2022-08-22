package org.rkm.ktdp.generators.date;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.ZoneId;

import static org.assertj.core.api.Assertions.assertThat;

class CurrentTest {

    @Test
    void shouldReturnCurrentDate() {
        Current current = new Current("YYYY-MM-dd", "UTC");
        assertThat(LocalDate.now(ZoneId.of("UTC")).toString())
                .contains(current.generate());

    }
}