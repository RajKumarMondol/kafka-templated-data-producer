package org.rkm.ktdp.templates.characters;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.curiousoddman.rgxgen.RgxGen;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
public class RandomString extends StringTemplate {
    private final RgxGen rgxGen;

    public RandomString(@JsonProperty(value = "name") String name,
                        @JsonProperty(value = "regex") String regex) {
        super(name);
        this.rgxGen = new RgxGen(regex);
    }

    @Override
    public String generate() {
        return rgxGen.generate();
    }
}
