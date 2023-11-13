package org.garvit.IronHide.kafka.consumers;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.HashMap;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.garvit.IronHide.models.AuditPublishDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.DefaultErrorHandler;
import org.springframework.kafka.support.ExponentialBackOffWithMaxRetries;
import org.springframework.kafka.support.serializer.JsonDeserializer;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class ConsumerConfigurations {

  private final ObjectMapper objectMapper;

  @Value("${spring.application.name}")
  private String clientId;

  @Value(value = "${spring.kafka.bootstrap-servers}")
  private String bootstrapAddress;

  /** Consumer Factory for consuming Audit */
  @Bean
  public ConcurrentKafkaListenerContainerFactory<String, AuditPublishDTO>
      auditKafkaListenerContainerFactory() {
    ConcurrentKafkaListenerContainerFactory<String, AuditPublishDTO> factory =
        new ConcurrentKafkaListenerContainerFactory<>();
    factory.setConsumerFactory(auditConsumerFactory());
    factory.setConcurrency(2);
    factory.setCommonErrorHandler(
        new DefaultErrorHandler(
            (record, e) -> {
                log.error(
                    "Exception while processing Audit Update Event with key: {} and value {}, Retrying",
                    record.key(),
                    record.value());
                log.error(e.getMessage(), e);
            },
            new ExponentialBackOffWithMaxRetries(3)));
    return factory;
  }

  private ConsumerFactory<String, AuditPublishDTO> auditConsumerFactory() {
    Map<String, Object> props = new HashMap<>();
    props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
    props.put(ConsumerConfig.GROUP_ID_CONFIG, clientId);
    props.put(ConsumerConfig.MAX_POLL_RECORDS_CONFIG, 500);
    return new DefaultKafkaConsumerFactory<>(
        props,
        new StringDeserializer(),
        new JsonDeserializer<>(AuditPublishDTO.class, objectMapper));
  }
}
