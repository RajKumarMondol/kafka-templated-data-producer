package org.rkm.ktdp.generators.number;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class SequenceTest {
    @Test
    void shouldGenerateIntegerInSequence() {
        Sequence sequence = new Sequence("%05.2f", 10.01, 0.5);
        assertThat(sequence.generate())
                .isEqualTo("10.01");
        assertThat(sequence.generate())
                .isEqualTo("10.51");
        assertThat(sequence.generate())
                .isEqualTo("11.01");
    }
}