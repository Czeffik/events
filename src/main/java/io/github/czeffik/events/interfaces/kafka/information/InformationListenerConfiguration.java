package io.github.czeffik.events.interfaces.kafka.information;

import io.github.czeffik.events.domain.information.InformationEventPublisher;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.time.Clock;
import java.util.Map;

@Configuration
public class InformationListenerConfiguration {

    @Bean
    InformationConsumer informationConsumer(
        InformationEventPublisher informationEventPublisher,
        Clock clock
    ) {
        return new InformationConsumer(informationEventPublisher, clock);
    }

    @Bean
    ConcurrentKafkaListenerContainerFactory<String, InformationDto> informationKafkaListenerFactory(
        ConsumerFactory<String, InformationDto> consumerFactory
    ) {
        ConcurrentKafkaListenerContainerFactory<String, InformationDto> cklcf =
            new ConcurrentKafkaListenerContainerFactory<>();

        cklcf.setConcurrency(1);
        cklcf.setConsumerFactory(consumerFactory);

        return cklcf;
    }

    @Bean
    ConsumerFactory<String, InformationDto> consumerFactory(KafkaProperties kafkaProperties) {
        Map<String, Object> config = kafkaProperties.buildConsumerProperties();

        return new DefaultKafkaConsumerFactory<>(
            config,
            new StringDeserializer(),
            new JsonDeserializer<>(InformationDto.class)
        );
    }

}
