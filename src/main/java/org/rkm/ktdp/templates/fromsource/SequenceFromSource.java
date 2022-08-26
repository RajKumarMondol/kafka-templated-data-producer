package org.rkm.ktdp.templates.fromsource;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import org.rkm.ktdp.datasource.BaseSource;

@EqualsAndHashCode(callSuper = true)
public class SequenceFromSource extends FromSourceTemplate {

    private int currentIndex;

    public SequenceFromSource(@JsonProperty(value = "name") String name,
                              @JsonProperty(value = "source") BaseSource source) {
        super(name, source);
        currentIndex = 0;
    }

    @Override
    public String generate() {
        return source.get(currentIndex++ % source.count());
    }
}
