package org.rkm.ktdp.templates.time;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class TimeSequenceTest {
    @Test
    void shouldReturnTimeInSequence() {
        TimeSequence timeSequence = new TimeSequence("name","HH:mm:ss", "10:00:01", 30);
        assertThat(timeSequence.generate())
                .isEqualTo("10:00:01");
        assertThat(timeSequence.generate())
                .isEqualTo("10:00:31");
        assertThat(timeSequence.generate())
                .isEqualTo("10:01:01");
    }
}