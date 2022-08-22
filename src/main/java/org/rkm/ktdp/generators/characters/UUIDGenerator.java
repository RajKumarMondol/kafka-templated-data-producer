package org.rkm.ktdp.generators.characters;

import lombok.EqualsAndHashCode;
import org.rkm.ktdp.generators.specification.BaseGenerator;

import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
public class UUIDGenerator extends StringGenerator {
    @Override
    public String generate() {
        return UUID.randomUUID().toString();
    }
}
