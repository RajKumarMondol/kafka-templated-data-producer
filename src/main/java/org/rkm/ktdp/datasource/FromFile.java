package org.rkm.ktdp.datasource;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
public class FromFile extends BaseSource {
    public final String filepath;

    public FromFile(@JsonProperty(value = "filepath") String filepath) {
        this.filepath = filepath;
    }
}
