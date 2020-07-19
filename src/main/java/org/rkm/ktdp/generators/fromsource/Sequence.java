package org.rkm.ktdp.generators.fromsource;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import org.rkm.ktdp.datasource.BaseSource;

@EqualsAndHashCode(callSuper = true)
public class Sequence extends FromSourceGenerator {

    public Sequence(@JsonProperty(value = "source") BaseSource source) {
        super(source);
    }
}
