package io.github.czeffik.events.interfaces.kafka

import io.github.czeffik.events.interfaces.kafka.deserializer.InformationDtoDeserializer
import io.github.czeffik.events.interfaces.kafka.information.InformationDto
import io.github.czeffik.events.interfaces.kafka.serializer.InformationDtoSerializer
import io.github.czeffik.kafka.test.clients.consumer.KafkaTestConsumer
import io.github.czeffik.kafka.test.clients.consumer.KafkaTestConsumerFactory
import io.github.czeffik.kafka.test.clients.helper.KafkaTestHelper
import io.github.czeffik.kafka.test.clients.helper.KafkaTestHelperFactory
import io.github.czeffik.kafka.test.clients.producer.KafkaTestProducer
import io.github.czeffik.kafka.test.clients.producer.KafkaTestProducerFactory
import org.apache.kafka.clients.consumer.ConsumerConfig
import org.apache.kafka.clients.consumer.KafkaConsumer
import org.apache.kafka.clients.consumer.OffsetResetStrategy
import org.apache.kafka.clients.producer.KafkaProducer
import org.apache.kafka.clients.producer.ProducerConfig
import org.apache.kafka.common.serialization.StringDeserializer
import org.apache.kafka.common.serialization.StringSerializer
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Lazy

import java.time.Duration

@TestConfiguration
class KafkaTestConfig {
    private static final String TEST_GROUP_ID = "TEST_GROUP_ID"

    @Lazy
    @Bean(destroyMethod = 'close')
    KafkaTestHelper<InformationDto> informationTopicHelper(
        @Value('${input.topic.information}') informationTopic,
        KafkaProducer<String, InformationDto> informationKafkaProducer,
        KafkaConsumer<String, InformationDto> informationKafkaConsumer

    ) {
        KafkaTestConsumer<InformationDto> consumer = KafkaTestConsumerFactory.createConsumer(
            informationKafkaConsumer,
            informationTopic,
            Duration.ofSeconds(2)
        )
        KafkaTestProducer<InformationDto> producer = KafkaTestProducerFactory.createProducer(
            informationKafkaProducer,
            informationTopic
        )
        return KafkaTestHelperFactory.createHelper(producer, consumer, Duration.ofSeconds(2))
    }

    @Lazy
    @Bean
    KafkaProducer<String, InformationDto> informationKafkaProducer(
        @Value('${spring.kafka.bootstrap-servers}') bootstrapServers
    ) {
        Properties props = new Properties()
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers)
        props.put(ProducerConfig.RETRIES_CONFIG, 0)
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class)
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, InformationDtoSerializer.class)
        return new KafkaProducer<String, InformationDto>(props)
    }

    @Lazy
    @Bean
    KafkaConsumer<String, InformationDto> informationKafkaConsumer(
        @Value('${spring.kafka.bootstrap-servers}') bootstrapServers
    ) {
        Properties props = new Properties()
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers)
        props.put(ConsumerConfig.GROUP_ID_CONFIG, TEST_GROUP_ID)
        props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, 'false')
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, OffsetResetStrategy.EARLIEST.name().toLowerCase())
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class)
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, InformationDtoDeserializer.class)
        return new KafkaConsumer<String, InformationDto>(props)
    }
}
