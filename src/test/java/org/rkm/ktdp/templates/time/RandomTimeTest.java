package org.rkm.ktdp.templates.time;

import org.junit.jupiter.api.Test;

import java.time.LocalTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.rkm.ktdp.helpers.TimeHelpers.getTimeInSeconds;

class RandomTimeTest {

    @Test
    void shouldGenerateRandomTimeWithinGivenRange() {
        RandomTime randomTime = new RandomTime("name","HH:mm:ss", "10:00:00", "18:00:00");
        for (int i = 0; i < 10; i++) {
            assertThat(getTimeInSeconds(LocalTime.parse(randomTime.generate())))
                    .isBetween(getTimeInSeconds(LocalTime.parse("10:00:00")), getTimeInSeconds(LocalTime.parse("18:00:00")));
        }
    }
}