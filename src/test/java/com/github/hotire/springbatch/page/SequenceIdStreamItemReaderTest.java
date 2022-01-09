package com.github.hotire.springbatch.page;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;

class SequenceIdStreamItemReaderTest  {

    @Test
    void read() throws Exception {
        // given
        final SequenceIdStreamItemReader<SequenceIdAware> reader = new SequenceIdStreamItemReader<>(param -> List.of(() -> 1L, () -> 2L), 100);

        // when
        final SequenceIdAware result = reader.read();

        // then
        assertThat(result).isNotNull();
        assertThat(result.getSequenceId()).isEqualTo(reader.lastSequenceId);
    }
}