package org.rkm.ktdp.datasource;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
public class FromValues extends BaseSource {
    public final List<String> values;

    public FromValues(@JsonProperty(value = "values") List<String> values) {
        this.values = values;
    }

    @Override
    public int count() {
        return values.size();
    }

    @Override
    public String get(int index) {
        return values.get(index);
    }
}