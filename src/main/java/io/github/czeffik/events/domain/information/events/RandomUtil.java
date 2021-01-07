package io.github.czeffik.events.domain.information.events;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.UUID;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class RandomUtil {

    public static String getRandomId() {
        return UUID.randomUUID().toString();
    }
}
