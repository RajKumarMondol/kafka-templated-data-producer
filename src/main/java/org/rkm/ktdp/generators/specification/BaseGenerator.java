package org.rkm.ktdp.generators.specification;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
@EqualsAndHashCode
@ToString
public abstract class BaseGenerator {
    public abstract String generate();
}
