package org.rkm.ktdp.templates.number;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class DoubleSequenceTest {
    @Test
    void shouldGenerateIntegerInSequence() {
        DoubleSequence doubleSequence = new DoubleSequence("name","%05.2f", 10.01, 0.5);
        assertThat(doubleSequence.generate())
                .isEqualTo("10.01");
        assertThat(doubleSequence.generate())
                .isEqualTo("10.51");
        assertThat(doubleSequence.generate())
                .isEqualTo("11.01");
    }
}