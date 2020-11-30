package io.github.czeffik.events.interfaces.kafka.serializer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.czeffik.events.interfaces.kafka.information.InformationDto;
import org.apache.kafka.common.serialization.Serializer;

public class InformationDtoSerializer implements Serializer<InformationDto> {

    private static final ObjectMapper mapper = new ObjectMapper();

    @Override
    public byte[] serialize(String topic, InformationDto data) {
        if (data == null) {
            return null;
        }
        try {
            return mapper.writeValueAsBytes(data);
        } catch (JsonProcessingException ex) {
            throw new RuntimeException(ex);
        }
    }
}
