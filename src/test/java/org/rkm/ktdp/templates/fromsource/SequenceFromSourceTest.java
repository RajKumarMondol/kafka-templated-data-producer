package org.rkm.ktdp.templates.fromsource;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.rkm.ktdp.datasource.BaseSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

class SequenceFromSourceTest {

    @Test
    void shouldReturnSequenceValuesFromSource() {
        BaseSource mockBaseSource = Mockito.mock(BaseSource.class);

        when(mockBaseSource.count())
                .thenReturn(2);
        when(mockBaseSource.get(0))
                .thenReturn("value1");
        when(mockBaseSource.get(1))
                .thenReturn("value2");

        SequenceFromSource sequenceFromSource = new SequenceFromSource("name",mockBaseSource);

        assertThat(sequenceFromSource.generate())
                .isEqualTo("value1");
        assertThat(sequenceFromSource.generate())
                .isEqualTo("value2");
        assertThat(sequenceFromSource.generate())
                .isEqualTo("value1");
    }
}