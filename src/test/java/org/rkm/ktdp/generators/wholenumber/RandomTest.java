package org.rkm.ktdp.generators.wholenumber;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class RandomTest {

    @Test
    void shouldGenerateRandomIntegerWithinGivenRange() {
        Random random = new Random("%03d", -100, 100);
        for (int i = 0; i < 10; i++) {
            assertThat(Integer.parseInt(random.generate()))
                    .isBetween(-100, 100);
        }
    }
}