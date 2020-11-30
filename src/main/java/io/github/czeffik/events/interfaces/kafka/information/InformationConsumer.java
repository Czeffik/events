package io.github.czeffik.events.interfaces.kafka.information;

import io.github.czeffik.events.domain.information.InformationEventPublisher;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;

@Slf4j
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
class InformationConsumer {
    private final InformationEventPublisher eventPublisher;

    @KafkaListener(topics = "${input.topic.information}", containerFactory = "informationKafkaListenerFactory")
    public void read(ConsumerRecord<String, InformationDto> record) {
        log.info("Consumed information! Key: [{}], information: [{}]!", record.key(), record.value());
        this.eventPublisher.publish(record.value().toInformationUpdateReceivedEvent());
    }
}
