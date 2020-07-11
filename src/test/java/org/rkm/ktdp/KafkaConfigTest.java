package org.rkm.ktdp;

import org.apache.kafka.clients.CommonClientConfigs;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.config.SaslConfigs;
import org.apache.kafka.common.config.SslConfigs;
import org.apache.kafka.common.serialization.StringSerializer;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.core.env.Environment;

import java.util.Properties;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

public class KafkaConfigTest {

    private Environment mockedEnvironment = Mockito.mock(Environment.class);

    @Test
    public void shouldCreateConfigWithSecurityProtocolPLAINTEXT() {
        mockCommonProducerConfigs();
        when(mockedEnvironment.getProperty("kafka.security.protocol"))
                .thenReturn("PLAINTEXT");

        Properties properties = new KafkaConfig().kafkaConfiguration(mockedEnvironment);

        verifyCommonProducerConfigs(properties);
        assertThat(properties.get(CommonClientConfigs.SECURITY_PROTOCOL_CONFIG))
                .isEqualTo("PLAINTEXT");
    }

    @Test
    public void shouldCreateConfigWithSecurityProtocolSSL() {
        mockCommonProducerConfigs();
        mockSSLConfigs();

        Properties properties = new KafkaConfig().kafkaConfiguration(mockedEnvironment);

        verifyCommonProducerConfigs(properties);
        verifySSLConfigs(properties);
    }

    @Test
    public void shouldCreateConfigWithSecurityProtocolSASL_SSL() {
        mockCommonProducerConfigs();
        mockConfigsForSASL_SSL();

        Properties properties = new KafkaConfig().kafkaConfiguration(mockedEnvironment);

        verifyCommonProducerConfigs(properties);
        verifyConfigsForSASL_SSL(properties);
    }

    @Test
    public void shouldCreateConfigWithSecurityProtocolSASL_PLAINTEXT() {
        mockCommonProducerConfigs();
        mockConfigsForSASL_PLAINTEXT();

        Properties properties = new KafkaConfig().kafkaConfiguration(mockedEnvironment);

        verifyCommonProducerConfigs(properties);
        verifyConfigsForSASL_PLAINTEXT(properties);
    }

    private void mockCommonProducerConfigs() {
        when(mockedEnvironment.getProperty("kafka.bootstrap.servers"))
                .thenReturn("bootstrapAddress:9092");
        when(mockedEnvironment.getProperty("kafka.reconnect.backoff.ms"))
                .thenReturn("1000");
        when(mockedEnvironment.getProperty("kafka.retry.backoff.ms"))
                .thenReturn("1001");
        when(mockedEnvironment.getProperty("kafka.max.block.ms"))
                .thenReturn("1002");
    }

    private void verifyCommonProducerConfigs(Properties properties) {
        assertThat(properties.get(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG))
                .isEqualTo("bootstrapAddress:9092");
        assertThat(properties.get(ProducerConfig.RECONNECT_BACKOFF_MS_CONFIG))
                .isEqualTo("1000");
        assertThat(properties.get(ProducerConfig.RETRY_BACKOFF_MS_CONFIG))
                .isEqualTo("1001");
        assertThat(properties.get(ProducerConfig.MAX_BLOCK_MS_CONFIG))
                .isEqualTo("1002");
        assertThat(properties.get(ProducerConfig.ENABLE_IDEMPOTENCE_CONFIG))
                .isEqualTo(true);
        assertThat(properties.get(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG))
                .isEqualTo(StringSerializer.class);
        assertThat(properties.get(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG))
                .isEqualTo(StringSerializer.class);
    }

    private void mockSSLConfigs() {
        when(mockedEnvironment.getProperty("kafka.security.protocol"))
                .thenReturn("SSL");
        when(mockedEnvironment.getProperty("kafka.ssl.truststore.location"))
                .thenReturn("truststoreLocation");
        when(mockedEnvironment.getProperty("kafka.ssl.truststore.password"))
                .thenReturn("truststorePassword");
        when(mockedEnvironment.getProperty("kafka.ssl.endpoint.identification.algorithm"))
                .thenReturn("sslIdentificationAlgo");
        when(mockedEnvironment.getProperty("kafka.ssl.keystore.location"))
                .thenReturn("keystoreLocation");
        when(mockedEnvironment.getProperty("kafka.ssl.keystore.password"))
                .thenReturn("keystorePassword");
        when(mockedEnvironment.getProperty("kafka.ssl.key.password"))
                .thenReturn("keyPassword");
    }

    private void verifySSLConfigs(Properties properties) {

        assertThat(properties.get(CommonClientConfigs.SECURITY_PROTOCOL_CONFIG))
                .isEqualTo("SSL");
        assertThat(properties.get(SslConfigs.SSL_ENDPOINT_IDENTIFICATION_ALGORITHM_CONFIG))
                .isEqualTo("sslIdentificationAlgo");
        assertThat(properties.get(SslConfigs.SSL_TRUSTSTORE_LOCATION_CONFIG))
                .isEqualTo("truststoreLocation");
        assertThat(properties.get(SslConfigs.SSL_TRUSTSTORE_PASSWORD_CONFIG))
                .isEqualTo("truststorePassword");
        assertThat(properties.get(SslConfigs.SSL_KEYSTORE_LOCATION_CONFIG))
                .isEqualTo("keystoreLocation");
        assertThat(properties.get(SslConfigs.SSL_KEYSTORE_PASSWORD_CONFIG))
                .isEqualTo("keystorePassword");
        assertThat(properties.get(SslConfigs.SSL_KEY_PASSWORD_CONFIG))
                .isEqualTo("keyPassword");
    }

    private void mockConfigsForSASL_SSL() {
        when(mockedEnvironment.getProperty("kafka.security.protocol"))
                .thenReturn("SASL_SSL");
        when(mockedEnvironment.getProperty("kafka.ssl.truststore.location"))
                .thenReturn("truststoreLocation");
        when(mockedEnvironment.getProperty("kafka.ssl.truststore.password"))
                .thenReturn("truststorePassword");
        when(mockedEnvironment.getProperty("kafka.ssl.endpoint.identification.algorithm"))
                .thenReturn("sslIdentificationAlgo");
        when(mockedEnvironment.getProperty("kafka.sasl.mechanism"))
                .thenReturn("saslMechanism");
        when(mockedEnvironment.getProperty("kafka.sasl.jaas.config"))
                .thenReturn("saslJaasConfig");
    }

    private void verifyConfigsForSASL_SSL(Properties properties) {

        assertThat(properties.get(CommonClientConfigs.SECURITY_PROTOCOL_CONFIG).toString())
                .isEqualTo("SASL_SSL");
        assertThat(properties.get(SslConfigs.SSL_ENDPOINT_IDENTIFICATION_ALGORITHM_CONFIG).toString())
                .isEqualTo("sslIdentificationAlgo");
        assertThat(properties.get(SslConfigs.SSL_TRUSTSTORE_LOCATION_CONFIG).toString())
                .isEqualTo("truststoreLocation");
        assertThat(properties.get(SslConfigs.SSL_TRUSTSTORE_PASSWORD_CONFIG).toString())
                .isEqualTo("truststorePassword");
        assertThat(properties.get(SaslConfigs.SASL_MECHANISM).toString())
                .isEqualTo("saslMechanism");
        assertThat(properties.get(SaslConfigs.SASL_JAAS_CONFIG).toString())
                .isEqualTo("saslJaasConfig");
    }

    private void mockConfigsForSASL_PLAINTEXT() {
        when(mockedEnvironment.getProperty("kafka.security.protocol"))
                .thenReturn("SASL_PLAINTEXT");
        when(mockedEnvironment.getProperty("kafka.sasl.mechanism"))
                .thenReturn("saslMechanism");
        when(mockedEnvironment.getProperty("kafka.sasl.jaas.config"))
                .thenReturn("saslJaasConfig");
    }

    private void verifyConfigsForSASL_PLAINTEXT(Properties properties) {
        assertThat(properties.get(CommonClientConfigs.SECURITY_PROTOCOL_CONFIG).toString())
                .isEqualTo("SASL_PLAINTEXT");
        assertThat(properties.get(SaslConfigs.SASL_MECHANISM).toString())
                .isEqualTo("saslMechanism");
        assertThat(properties.get(SaslConfigs.SASL_JAAS_CONFIG).toString())
                .isEqualTo("saslJaasConfig");
    }
}