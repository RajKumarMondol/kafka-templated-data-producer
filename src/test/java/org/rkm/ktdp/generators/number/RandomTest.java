package org.rkm.ktdp.generators.number;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class RandomTest {
    @Test
    void shouldGenerateRandomIntegerWithinGivenRange() {
        Random random = new Random("%5.2f", -10.0, 10.0);
        for (int i = 0; i < 10; i++) {
            assertThat(Double.parseDouble(random.generate()))
                    .isBetween(-10.0, 10.0);
        }
    }
}