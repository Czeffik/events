package io.github.czeffik.events

import java.time.Clock
import java.time.Instant
import java.time.ZoneId

class TimeHelper {
    static final Instant FIXED_TIMESTAMP = Instant.parse('2021-01-07T10:00:00Z')
    static final Clock FIXED_CLOCK = Clock.fixed(FIXED_TIMESTAMP, ZoneId.of('UTC'))
}
