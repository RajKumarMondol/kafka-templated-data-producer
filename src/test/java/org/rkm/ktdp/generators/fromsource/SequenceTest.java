package org.rkm.ktdp.generators.fromsource;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.rkm.ktdp.datasource.BaseSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

class SequenceTest {

    @Test
    void shouldReturnSequenceValuesFromSource() {
        BaseSource mockBaseSource = Mockito.mock(BaseSource.class);

        when(mockBaseSource.count())
                .thenReturn(2);
        when(mockBaseSource.get(0))
                .thenReturn("value1");
        when(mockBaseSource.get(1))
                .thenReturn("value2");

        Sequence sequence = new Sequence(mockBaseSource);

        assertThat(sequence.generate())
                .isEqualTo("value1");
        assertThat(sequence.generate())
                .isEqualTo("value2");
        assertThat(sequence.generate())
                .isEqualTo("value1");
    }
}