package io.github.czeffik.events.interfaces.kafka

import io.github.czeffik.events.domain.information.InformationEventPublisher
import io.github.czeffik.events.domain.information.events.InformationUpdateReceivedEvent
import io.github.czeffik.events.interfaces.kafka.information.InformationDto
import io.github.czeffik.kafka.test.clients.helper.KafkaTestHelper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.kafka.config.KafkaListenerEndpointRegistry
import org.springframework.kafka.test.utils.ContainerTestUtils
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.TestPropertySource
import spock.lang.Specification

@ActiveProfiles(['test'])
@SpringBootTest(
    classes = [
        KafkaInterfacesConfiguration.class,
        KafkaTestConfig.class,
        InformationConsumerTestConfig.class
    ]
)
@TestPropertySource(
    properties = [
        'input.topic.information=TopicInformationConsumerIT'
    ]
)
@EmbeddedKafkaTest
class InformationConsumerIT extends Specification {

    @Autowired
    KafkaTestHelper<InformationDto> informationTopicHelper

    @Autowired
    InformationEventPublisher informationEventPublisherMock

    @Autowired
    KafkaListenerEndpointRegistry kafkaListenerEndpointRegistry

    def setup() {
        kafkaListenerEndpointRegistry.getListenerContainers().each { ContainerTestUtils.waitForAssignment(it, EmbeddedKafkaTest.PARTITIONS) }
    }

    def 'should consume message and publish InformationUpdateReceivedEvent'() {
        given:
            def incomingInformation = new InformationDto(id: 'id', name: 'name', description: 'description')
        when:
            informationTopicHelper.sendMessageAndWaitToAppear(incomingInformation.getId(), incomingInformation)
        then:
            1 * informationEventPublisherMock.publish(_ as InformationUpdateReceivedEvent)
    }
}
