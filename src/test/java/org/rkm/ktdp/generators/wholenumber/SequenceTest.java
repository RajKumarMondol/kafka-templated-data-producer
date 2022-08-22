package org.rkm.ktdp.generators.wholenumber;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class SequenceTest {

    @Test
    void shouldGenerateIntegerInSequence() {
        Sequence integerSequence = new Sequence("%05d", 100,5);
        assertThat(integerSequence.generate())
                .isEqualTo("00100");
        assertThat(integerSequence.generate())
                .isEqualTo("00105");
        assertThat(integerSequence.generate())
                .isEqualTo("00110");
    }
}