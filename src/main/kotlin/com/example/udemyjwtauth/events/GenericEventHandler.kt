package com.example.udemyjwtauth.events

import com.launchdarkly.eventsource.MessageEvent
import com.launchdarkly.eventsource.background.BackgroundEventHandler
import org.slf4j.Logger
import org.springframework.stereotype.Service

@Service
class GenericEventHandler(
    val logger: Logger
):BackgroundEventHandler{
    override fun onOpen() {

    }

    override fun onClosed() {

    }

    override fun onMessage(event: String?, messageEvent: MessageEvent?) {
       logger.info("This is an event $messageEvent")
    }

    override fun onComment(comment: String?) {

    }

    override fun onError(t: Throwable?) {

    }
}