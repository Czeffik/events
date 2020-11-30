package io.github.czeffik.events.interfaces.kafka

import org.springframework.kafka.test.context.EmbeddedKafka
import org.springframework.test.annotation.DirtiesContext
import org.springframework.test.context.TestPropertySource

import java.lang.annotation.ElementType
import java.lang.annotation.Retention
import java.lang.annotation.RetentionPolicy
import java.lang.annotation.Target

@DirtiesContext
@EmbeddedKafka(
    topics = [
        '${input.topic.information:information_topic}'
    ],
    partitions = EmbeddedKafkaTest.PARTITIONS
)
@TestPropertySource(
    properties = ['spring.kafka.bootstrap-servers=${spring.embedded.kafka.brokers}']
)
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@interface EmbeddedKafkaTest {

    int PARTITIONS = 4;

}
