package org.rkm.ktdp.datasource;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class FromFileTest {

    @Test
    void shouldReturnValueFromValueSource() {
        String fileSourcePath = getClass().getClassLoader().getResource("values.txt").getFile();
        FromFile fromFile = new FromFile(fileSourcePath);

        assertThat(fromFile.count())
                .isEqualTo(3);
        assertThat(fromFile.get(0))
                .isEqualTo("line1");
        assertThat(fromFile.get(1))
                .isEqualTo("line2");
        assertThat(fromFile.get(2))
                .isEqualTo("line3");
    }
}