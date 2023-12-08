package org.garvit.IronHide.kafka.consumers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.garvit.IronHide.models.AuditPublishDTO;
import org.garvit.IronHide.services.AuditServicePort;
import org.garvit.IronHide.utilities.ValidationUtils;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
@ConditionalOnProperty(value = "spring.kafka.enabled", havingValue = "true", matchIfMissing = true)
public class AuditConsumer {

  private final AuditServicePort auditService;

  /**
   * Kafka listener for the "audit" topic. This method processes messages received on this topic.
   * The key of each message is expected to be a UUID, which uniquely identifies each message. Using
   * UUIDs as keys facilitates easy tracking and logging of message consumption, enhancing
   * traceability and debuggability.
   *
   * @see <a
   *     href="https://forum.confluent.io/t/what-should-i-use-as-the-key-for-my-kafka-message/312/1">Key
   *     for kafka Messages</a>
   * @param stringAuditPublishDTOConsumerRecord The ConsumerRecord object, containing the UUID key and the
   *     AuditPublishDTO message.
   */
  @KafkaListener(topics = "audit", containerFactory = "auditKafkaListenerContainerFactory")
  public void auditListener(ConsumerRecord<String, AuditPublishDTO> stringAuditPublishDTOConsumerRecord) {
    log.info("Consuming listener for uuid: {}", stringAuditPublishDTOConsumerRecord.key());
    stringAuditPublishDTOConsumerRecord.value().setUuid(stringAuditPublishDTOConsumerRecord.key());
    auditService.saveAudit(stringAuditPublishDTOConsumerRecord.value());
  }
}
