package org.rkm.ktdp.templates.date;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class DateSequenceTest {

    @Test
    void shouldReturnDateInSequence() {
        DateSequence sequenceDate = new DateSequence("name","yyyy-MM-dd", "2022-08-24", 5);
        assertThat(sequenceDate.generate())
                .isEqualTo("2022-08-24");
        assertThat(sequenceDate.generate())
                .isEqualTo("2022-08-29");
        assertThat(sequenceDate.generate())
                .isEqualTo("2022-09-03");
    }
}