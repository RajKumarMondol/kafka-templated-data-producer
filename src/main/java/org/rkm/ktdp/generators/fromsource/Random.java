package org.rkm.ktdp.generators.fromsource;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import org.rkm.ktdp.datasource.BaseSource;

@EqualsAndHashCode(callSuper = true)
public class Random extends FromSourceGenerator {

    public Random(@JsonProperty(value = "source") BaseSource source) {
        super(source);
    }

    @Override
    public String generate() {
        return source.get((int) (Math.random() * source.count()) - 1);
    }

}
