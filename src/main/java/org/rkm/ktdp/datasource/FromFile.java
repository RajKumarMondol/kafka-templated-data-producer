package org.rkm.ktdp.datasource;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.SneakyThrows;
import org.springframework.util.StringUtils;

import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
public class FromFile extends BaseSource {
    public final List<String> lines;

    @SneakyThrows
    public FromFile(@JsonProperty(value = "filepath") String filepath) {
        this.lines = new ArrayList();
        BufferedReader reader = new BufferedReader(new java.io.FileReader(filepath));
        String line = reader.readLine();
        while (!StringUtils.isEmpty(line)) {
            lines.add(line.trim());
            line = reader.readLine();
        }
        reader.close();
    }

    @Override
    public int count() {
        return lines.size();
    }

    @Override
    public String get(int index) {
        return lines.get(index);
    }
}
