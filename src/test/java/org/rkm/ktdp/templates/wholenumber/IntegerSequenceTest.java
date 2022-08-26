package org.rkm.ktdp.templates.wholenumber;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class IntegerSequenceTest {

    @Test
    void shouldGenerateIntegerInSequence() {
        IntegerSequence integerSequence = new IntegerSequence("name","%05d", 100,5);
        assertThat(integerSequence.generate())
                .isEqualTo("00100");
        assertThat(integerSequence.generate())
                .isEqualTo("00105");
        assertThat(integerSequence.generate())
                .isEqualTo("00110");
    }
}