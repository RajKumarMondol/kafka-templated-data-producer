package org.rkm.ktdp.templates.fromsource;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import org.rkm.ktdp.datasource.BaseSource;

@EqualsAndHashCode(callSuper = true)
public class RandomFromSource extends FromSourceTemplate {

    public RandomFromSource(@JsonProperty(value = "name") String name,
                            @JsonProperty(value = "source") BaseSource source) {
        super(name, source);
    }

    @Override
    public String generate() {
        return source.get((int) (Math.random() * source.count()) % source.count());
    }

}
