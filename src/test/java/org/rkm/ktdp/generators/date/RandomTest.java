package org.rkm.ktdp.generators.date;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.rkm.ktdp.helpers.TimeHelpers.getTimeInDays;

class RandomTest {

    @Test
    void shouldGenerateRandomTimeWithinGivenRange() {
        Random random = new Random("yyyy-MM-dd", "2022-08-24", "2022-09-24");
        for (int i = 0; i < 10; i++) {
            assertThat(getTimeInDays(LocalDate.parse(random.generate())))
                    .isBetween(getTimeInDays(LocalDate.parse("2022-08-24")), getTimeInDays(LocalDate.parse("2022-09-24")));
        }
    }
}