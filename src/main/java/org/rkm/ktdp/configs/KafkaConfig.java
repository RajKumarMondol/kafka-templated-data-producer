package org.rkm.ktdp.configs;

import org.apache.kafka.clients.CommonClientConfigs;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.config.SaslConfigs;
import org.apache.kafka.common.config.SslConfigs;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import java.util.Properties;

@Configuration
public class KafkaConfig {
    private static final String CONFIG_PREFIX = "kafka.";

    @Bean(name = "kafkaConfiguration")
    public Properties kafkaConfiguration(Environment environment) {
        final Properties props = new Properties();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,
                environment.getProperty(CONFIG_PREFIX + ProducerConfig.BOOTSTRAP_SERVERS_CONFIG));
        props.put(ProducerConfig.RECONNECT_BACKOFF_MS_CONFIG,
                environment.getProperty(CONFIG_PREFIX + ProducerConfig.RECONNECT_BACKOFF_MS_CONFIG));
        props.put(ProducerConfig.RETRY_BACKOFF_MS_CONFIG,
                environment.getProperty(CONFIG_PREFIX + ProducerConfig.RETRY_BACKOFF_MS_CONFIG));
        props.put(ProducerConfig.MAX_BLOCK_MS_CONFIG,
                environment.getProperty(CONFIG_PREFIX + ProducerConfig.MAX_BLOCK_MS_CONFIG));
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,
                StringSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,
                StringSerializer.class);
        props.put(ProducerConfig.ENABLE_IDEMPOTENCE_CONFIG,
                true);

        String securityProtocol = environment.getProperty(CONFIG_PREFIX + CommonClientConfigs.SECURITY_PROTOCOL_CONFIG);

        props.put(CommonClientConfigs.SECURITY_PROTOCOL_CONFIG,
                securityProtocol);

        if ("SSL".equals(securityProtocol)) {
            configureSSL(props, environment);
        } else if ("SASL_SSL".equals(securityProtocol)) {
            configureSASL_SSL(props, environment);
        } else if ("SASL_PLAINTEXT".equals(securityProtocol)) {
            configureSASL_PLAINTEXT(props, environment);
        }
        return props;
    }

    private void configureSASL_PLAINTEXT(Properties props, Environment environment) {
        props.put(SaslConfigs.SASL_MECHANISM,
                environment.getProperty(CONFIG_PREFIX + SaslConfigs.SASL_MECHANISM));
        props.put(SaslConfigs.SASL_JAAS_CONFIG,
                environment.getProperty(CONFIG_PREFIX + SaslConfigs.SASL_JAAS_CONFIG));
    }

    private void configureSASL_SSL(Properties props, Environment environment) {
        props.put(SslConfigs.SSL_ENDPOINT_IDENTIFICATION_ALGORITHM_CONFIG,
                environment.getProperty(CONFIG_PREFIX + SslConfigs.SSL_ENDPOINT_IDENTIFICATION_ALGORITHM_CONFIG));
        props.put(SslConfigs.SSL_TRUSTSTORE_LOCATION_CONFIG,
                environment.getProperty(CONFIG_PREFIX + SslConfigs.SSL_TRUSTSTORE_LOCATION_CONFIG));
        props.put(SslConfigs.SSL_TRUSTSTORE_PASSWORD_CONFIG,
                environment.getProperty(CONFIG_PREFIX + SslConfigs.SSL_TRUSTSTORE_PASSWORD_CONFIG));
        props.put(SaslConfigs.SASL_MECHANISM,
                environment.getProperty(CONFIG_PREFIX + SaslConfigs.SASL_MECHANISM));
        props.put(SaslConfigs.SASL_JAAS_CONFIG,
                environment.getProperty(CONFIG_PREFIX + SaslConfigs.SASL_JAAS_CONFIG));
    }

    private void configureSSL(Properties props, Environment environment) {
        props.put(SslConfigs.SSL_ENDPOINT_IDENTIFICATION_ALGORITHM_CONFIG,
                environment.getProperty(CONFIG_PREFIX + SslConfigs.SSL_ENDPOINT_IDENTIFICATION_ALGORITHM_CONFIG));
        props.put(SslConfigs.SSL_TRUSTSTORE_LOCATION_CONFIG,
                environment.getProperty(CONFIG_PREFIX + SslConfigs.SSL_TRUSTSTORE_LOCATION_CONFIG));
        props.put(SslConfigs.SSL_TRUSTSTORE_PASSWORD_CONFIG,
                environment.getProperty(CONFIG_PREFIX + SslConfigs.SSL_TRUSTSTORE_PASSWORD_CONFIG));
        props.put(SslConfigs.SSL_KEYSTORE_LOCATION_CONFIG,
                environment.getProperty(CONFIG_PREFIX + SslConfigs.SSL_KEYSTORE_LOCATION_CONFIG));
        props.put(SslConfigs.SSL_KEYSTORE_PASSWORD_CONFIG,
                environment.getProperty(CONFIG_PREFIX + SslConfigs.SSL_KEYSTORE_PASSWORD_CONFIG));
        props.put(SslConfigs.SSL_KEY_PASSWORD_CONFIG,
                environment.getProperty(CONFIG_PREFIX + SslConfigs.SSL_KEY_PASSWORD_CONFIG));
    }
}
