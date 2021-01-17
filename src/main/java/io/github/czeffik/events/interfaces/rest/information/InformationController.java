package io.github.czeffik.events.interfaces.rest.information;

import io.github.czeffik.events.domain.information.InformationEventPublisher;
import io.github.czeffik.events.domain.information.InformationService;
import io.github.czeffik.events.domain.information.events.InformationEvent;
import io.github.czeffik.events.domain.information.events.StartProcessingEvent;
import io.github.czeffik.events.infrastructure.rest.emitter.EmitterStore;
import lombok.RequiredArgsConstructor;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.FluxSink;

import java.time.Instant;

@RestController
@RequiredArgsConstructor
class InformationController {
    private final EmitterStore<InformationEvent> emitterStore;
    private final InformationEventPublisher publisher;

    @GetMapping("/emit")
    public String emit() {
        Instant now = Instant.now();
        StartProcessingEvent event = StartProcessingEvent.builder()
            .timestamp(now)
            .information(InformationService.Information.builder()
                .id("ID" + now.getEpochSecond())
                .description("DESCRIPTION" + now.getEpochSecond())
                .name("NAME" + now.getEpochSecond())
                .build())
            .build();
        publisher.publish(event);
        return event.toString();
    }

    @GetMapping("/events")
    public Flux<ServerSentEvent<InformationDto>> events() {
        return Flux.create(sink -> this.emitterStore.add(sink::next), FluxSink.OverflowStrategy.LATEST)
            .map(event -> {
                InformationEvent informationEvent = (InformationEvent) event;
                return ServerSentEvent.<InformationDto>builder()
                    .id(informationEvent.getId())
                    .event(informationEvent.getClass().getSimpleName())
                    .data(InformationDto.from(informationEvent))
                    .build();
            });
    }
}
