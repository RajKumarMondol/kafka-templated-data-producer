package org.rkm.ktdp.datasource;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

class FromValuesTest {

    @Test
    void shouldReturnValueFromValueSource() {
        FromValues fromValues = new FromValues(Arrays.asList("1", "2"));

        assertThat(fromValues.count())
                .isEqualTo(2);
        assertThat(fromValues.get(0))
                .isEqualTo("1");
        assertThat(fromValues.get(1))
                .isEqualTo("2");
    }
}