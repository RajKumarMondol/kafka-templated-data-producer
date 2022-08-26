package org.rkm.ktdp.templates.date;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.rkm.ktdp.helpers.TimeHelpers.getTimeInDays;

class RandomDateTest {

    @Test
    void shouldGenerateRandomTimeWithinGivenRange() {
        RandomDate randomDate = new RandomDate("name","yyyy-MM-dd", "2022-08-24", "2022-09-24");
        for (int i = 0; i < 10; i++) {
            assertThat(getTimeInDays(LocalDate.parse(randomDate.generate())))
                    .isBetween(getTimeInDays(LocalDate.parse("2022-08-24")), getTimeInDays(LocalDate.parse("2022-09-24")));
        }
    }
}