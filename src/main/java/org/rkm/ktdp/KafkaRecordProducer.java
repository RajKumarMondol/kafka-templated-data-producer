package org.rkm.ktdp;

import lombok.SneakyThrows;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.slf4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
public class KafkaRecordProducer {
    private final Logger logger;
    private final KafkaProducer<String, String> kafkaProducer;

    public KafkaRecordProducer(Logger logger, KafkaProducer kafkaProducer) {
        this.logger = logger;
        this.kafkaProducer = kafkaProducer;
    }

    @SneakyThrows
    public void produceSingleRecord(String topic, String key, String message) {
        try {
            if (StringUtils.isEmpty(key)) {
                kafkaProducer.send(new ProducerRecord<>(topic, message)).get();
            } else {
                kafkaProducer.send(new ProducerRecord<>(topic, key, message)).get();
            }
        } catch (Exception exception) {
            logger.error("Error occurred while producing kafka record.", exception);
            throw exception;
        }
    }
}


