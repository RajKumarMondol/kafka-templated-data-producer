package org.rkm.ktdp;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.slf4j.Logger;
import org.springframework.util.concurrent.ListenableFuture;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class KafkaRecordProducerTest {
    private Logger mockLogger = Mockito.mock(Logger.class);
    private KafkaProducer mockedKafkaProducer = Mockito.mock(KafkaProducer.class);

    private KafkaRecordProducer kafkaRecordProducer = new KafkaRecordProducer(mockLogger, mockedKafkaProducer);

    @Test
    public void shouldProduceKafkaMessageWithKey() {

        when(mockedKafkaProducer.send(any()))
                .thenReturn(Mockito.mock(ListenableFuture.class));

        kafkaRecordProducer.produceSingleRecord("topicName", "", "message");

        verify(mockedKafkaProducer)
                .send(argThat((ProducerRecord<String, String> producerRecord) ->
                        producerRecord.topic().equals("topicName")
                                && producerRecord.value().equals("message")));
    }

    @Test
    public void shouldProduceKafkaMessageWithoutKey() {

        when(mockedKafkaProducer.send(any()))
                .thenReturn(Mockito.mock(ListenableFuture.class));

        kafkaRecordProducer.produceSingleRecord("topicName", "key", "message");

        verify(mockedKafkaProducer)
                .send(argThat((ProducerRecord<String, String> producerRecord) ->
                        producerRecord.topic().equals("topicName")
                                && producerRecord.key().equals("key")
                                && producerRecord.value().equals("message")));
    }

    @Test
    public void shouldLogWhenErrorOccursWhileProducingMessages() {
        RuntimeException testError = new RuntimeException("test");

        when(mockedKafkaProducer.send(any()))
                .thenThrow(testError);

        assertThrows(RuntimeException.class, () -> kafkaRecordProducer
                .produceSingleRecord("topicName", "key", "message"));

        verify(mockLogger)
                .error("Error occurred while producing kafka record.", testError);
    }
}