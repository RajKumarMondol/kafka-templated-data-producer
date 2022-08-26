package org.rkm.ktdp.templates.wholenumber;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class RandomIntegerTest {

    @Test
    void shouldGenerateRandomIntegerWithinGivenRange() {
        RandomInteger randomInteger = new RandomInteger("name","%03d", -100, 100);
        for (int i = 0; i < 10; i++) {
            assertThat(Integer.parseInt(randomInteger.generate()))
                    .isBetween(-100, 100);
        }
    }
}