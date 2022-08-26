package org.rkm.ktdp.templates.datetime;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.rkm.ktdp.helpers.TimeHelpers.getTimeInSeconds;

class RandomDateTimeTest {

    @Test
    void shouldGenerateRandomDateTimeWithinGivenRange() {
        RandomDateTime randomDateTime = new RandomDateTime("name","YYYY-MM-dd HH:mm:ss.SSS", "UTC", "2022-08-24 10:00:00.000", "2022-09-24 18:00:00.000");
        for (int i = 0; i < 10; i++) {
            assertThat(getTimeInSeconds(LocalDateTime.parse(randomDateTime.generate())))
                    .isBetween(getTimeInSeconds(LocalDateTime.parse("2022-08-24T10:00:00")),
                            getTimeInSeconds(LocalDateTime.parse("2022-09-24T18:00:00")));
        }
    }
}