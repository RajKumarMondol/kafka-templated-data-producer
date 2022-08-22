package org.rkm.ktdp.generators.characters;

import org.junit.jupiter.api.Test;

import java.util.regex.Pattern;

import static org.assertj.core.api.Assertions.assertThat;

class RandomTest {

    private final String REGEX = "[^0-9]*[12]?[0-9]{1,2}[^0-9]*";
    private final Pattern pattern = Pattern.compile(REGEX);

    @Test
    void shouldGenerateRandomWithinGivenRegx() {
        Random random = new Random(REGEX);
        for (int i = 0; i < 10; i++) {
            assertThat(pattern.matcher(random.generate()).matches())
                    .isTrue();
        }
    }
}