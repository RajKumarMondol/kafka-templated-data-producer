package org.rkm.ktdp.generators.time;

import org.junit.jupiter.api.Test;

import java.time.LocalTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.rkm.ktdp.helpers.TimeHelpers.getTimeInSeconds;

class RandomTest {

    @Test
    void shouldGenerateRandomTimeWithinGivenRange() {
        Random random = new Random("HH:mm:ss", "10:00:00", "18:00:00");
        for (int i = 0; i < 10; i++) {
            assertThat(getTimeInSeconds(LocalTime.parse(random.generate())))
                    .isBetween(getTimeInSeconds(LocalTime.parse("10:00:00")), getTimeInSeconds(LocalTime.parse("18:00:00")));
        }
    }
}