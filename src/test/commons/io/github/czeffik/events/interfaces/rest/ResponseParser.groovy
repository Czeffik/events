package io.github.czeffik.events.interfaces.rest

import groovy.json.JsonSlurper
import io.restassured.response.Response

trait ResponseParser {
    abstract JsonSlurper getSlurper()

    def parseResponse(Response response) {
        return slurper.parseText(response.body().asString())
    }
}
