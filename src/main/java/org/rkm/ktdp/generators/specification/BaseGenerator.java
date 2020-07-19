package org.rkm.ktdp.generators.specification;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.EqualsAndHashCode;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
@EqualsAndHashCode
public abstract class BaseGenerator {
}

