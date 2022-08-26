package org.rkm.ktdp.templates.number;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class RandomDoubleTest {
    @Test
    void shouldGenerateRandomIntegerWithinGivenRange() {
        RandomDouble randomDouble = new RandomDouble("name","%5.2f", -10.0, 10.0);
        for (int i = 0; i < 10; i++) {
            assertThat(Double.parseDouble(randomDouble.generate()))
                    .isBetween(-10.0, 10.0);
        }
    }
}