package org.rkm.ktdp.datasource;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.EqualsAndHashCode;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = FromFile.class),
        @JsonSubTypes.Type(value = FromValues.class),
})
@EqualsAndHashCode
public abstract class BaseSource {
}
