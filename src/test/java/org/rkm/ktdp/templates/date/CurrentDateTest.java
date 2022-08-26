package org.rkm.ktdp.templates.date;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.ZoneId;

import static org.assertj.core.api.Assertions.assertThat;

class CurrentDateTest {

    @Test
    void shouldReturnCurrentDate() {
        CurrentDate currentDate = new CurrentDate("name","YYYY-MM-dd", "UTC");
        assertThat(LocalDate.now(ZoneId.of("UTC")).toString())
                .contains(currentDate.generate());

    }
}