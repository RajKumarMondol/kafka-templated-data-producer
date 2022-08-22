package org.rkm.ktdp.generators.characters;

import org.junit.jupiter.api.Test;
import org.rkm.ktdp.generators.characters.UUIDGenerator;

import java.util.regex.Pattern;

import static org.assertj.core.api.Assertions.assertThat;

class UUIDGeneratorTest {
    private final String REGEX_FOR_UUID = "^[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}$";
    private final Pattern uuidPattern = Pattern.compile(REGEX_FOR_UUID);

    @Test
    void shouldGenerateStringInUUIDFormat() {
        assertThat(uuidPattern.matcher(new UUIDGenerator().generate()).matches())
                .isTrue();
    }
}