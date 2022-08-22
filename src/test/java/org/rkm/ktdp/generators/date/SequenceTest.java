package org.rkm.ktdp.generators.date;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class SequenceTest {

    @Test
    void shouldReturnDateInSequence() {
        Sequence sequence = new Sequence("yyyy-MM-dd", "2022-08-24", 5);
        assertThat(sequence.generate())
                .isEqualTo("2022-08-24");
        assertThat(sequence.generate())
                .isEqualTo("2022-08-29");
        assertThat(sequence.generate())
                .isEqualTo("2022-09-03");
    }
}