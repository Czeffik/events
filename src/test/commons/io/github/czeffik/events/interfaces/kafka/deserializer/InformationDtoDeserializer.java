package io.github.czeffik.events.interfaces.kafka.deserializer;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.czeffik.events.interfaces.kafka.information.InformationDto;
import org.apache.kafka.common.serialization.Deserializer;

import java.io.IOException;

public class InformationDtoDeserializer implements Deserializer<InformationDto> {

    private static final ObjectMapper mapper = new ObjectMapper();

    @Override
    public InformationDto deserialize(String topic, byte[] data) {
        if (data == null) {
            return null;
        }
        try {
            return mapper.readValue(data, InformationDto.class);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }
}
