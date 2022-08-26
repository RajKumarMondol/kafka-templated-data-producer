package org.rkm.ktdp.templates.characters;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;

import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
public class UUIDString extends StringTemplate {
    public UUIDString(@JsonProperty(value = "name") String name) {
        super(name);
    }

    @Override
    public String generate() {
        return UUID.randomUUID().toString();
    }
}
