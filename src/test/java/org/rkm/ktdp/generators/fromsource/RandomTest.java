package org.rkm.ktdp.generators.fromsource;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.rkm.ktdp.datasource.BaseSource;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

class RandomTest {

    @Test
    void shouldReturnRandomValuesFromSource() {
        BaseSource mockBaseSource = Mockito.mock(BaseSource.class);

        when(mockBaseSource.count())
                .thenReturn(2);
        when(mockBaseSource.get(0))
                .thenReturn("value1");
        when(mockBaseSource.get(1))
                .thenReturn("value2");
        when(mockBaseSource.get(2))
                .thenReturn("value3");
        when(mockBaseSource.get(3))
                .thenReturn("value4");

        Sequence sequence = new Sequence(mockBaseSource);

        List<String> expectedValues = Arrays.asList("value1", "value2", "value3", "value4");
        for (int i = 0; i < 10; i++) {
            assertThat(expectedValues.contains(sequence.generate()))
                    .isTrue();
        }
    }
}