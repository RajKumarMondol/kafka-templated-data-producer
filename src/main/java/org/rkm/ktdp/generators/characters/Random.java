package org.rkm.ktdp.generators.characters;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.curiousoddman.rgxgen.RgxGen;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
public class Random extends StringGenerator {
    private final RgxGen rgxGen;

    public Random(@JsonProperty(value = "regex") String regex) {
        this.rgxGen = new RgxGen(regex);
    }

    @Override
    public String generate() {
        return rgxGen.generate();
    }
}
