package io.github.czeffik.events.interfaces.rest.information;

import io.github.czeffik.events.domain.information.InformationEventPublisher;
import io.github.czeffik.events.domain.information.events.InformationEvent;
import io.github.czeffik.events.infrastructure.rest.emitter.EmitterStore;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;

@Configuration
public class InformationRestControllerConfiguration {

    @Bean
    InformationController informationController(
        EmitterStore<InformationEvent> informationEventEmitterStore,
        InformationEventPublisher informationEventPublisher
    ) {
        return new InformationController(informationEventEmitterStore, informationEventPublisher);
    }

    @Bean
    public RouterFunction<ServerResponse> indexRouter(@Value("classpath:/static/index.html") final Resource indexHtml) {
        return route(GET("/"), request -> ok().contentType(MediaType.TEXT_HTML).bodyValue(indexHtml));
    }

}
