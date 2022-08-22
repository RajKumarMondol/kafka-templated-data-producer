package org.rkm.ktdp.generators.datetime;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.rkm.ktdp.helpers.TimeHelpers.getTimeInSeconds;

class RandomTest {

    @Test
    void shouldGenerateRandomDateTimeWithinGivenRange() {
        Random random = new Random("YYYY-MM-dd'T'HH:mm:ss", "2022-08-24T10:00:00", "2022-09-24T18:00:00");
        for (int i = 0; i < 10; i++) {
            assertThat(getTimeInSeconds(LocalDateTime.parse(random.generate())))
                    .isBetween(getTimeInSeconds(LocalDateTime.parse("2022-08-24T10:00:00")),
                            getTimeInSeconds(LocalDateTime.parse("2022-09-24T18:00:00")));
        }
    }
}