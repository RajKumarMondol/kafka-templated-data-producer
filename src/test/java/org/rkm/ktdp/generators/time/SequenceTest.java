package org.rkm.ktdp.generators.time;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class SequenceTest {
    @Test
    void shouldReturnTimeInSequence() {
        Sequence sequence = new Sequence("HH:mm:ss", "10:00:01", 30);
        assertThat(sequence.generate())
                .isEqualTo("10:00:01");
        assertThat(sequence.generate())
                .isEqualTo("10:00:31");
        assertThat(sequence.generate())
                .isEqualTo("10:01:01");
    }
}