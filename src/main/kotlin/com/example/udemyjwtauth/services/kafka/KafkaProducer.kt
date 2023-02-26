package com.example.udemyjwtauth.services.kafka

import com.example.udemyjwtauth.dto.EarthQuakeDTO
import com.example.udemyjwtauth.dto.IssDTO
import com.example.udemyjwtauth.dto.KafkaRequest
import com.example.udemyjwtauth.dto.UpsResponseDto
import com.example.udemyjwtauth.events.GenericEventHandler
import com.launchdarkly.eventsource.EventSource
import org.slf4j.Logger
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.kafka.support.KafkaHeaders
import org.springframework.messaging.Message
import org.springframework.messaging.support.MessageBuilder
import org.springframework.stereotype.Service
import com.launchdarkly.eventsource.background.BackgroundEventSource
import java.net.URI
import java.util.concurrent.TimeUnit

@Service
class KafkaProducer(
    val kafkaTemplate: KafkaTemplate<String, String>,
    val logger: Logger
) {
    fun sendMessage(kafkaRequest: KafkaRequest) {
        val message: Message<KafkaRequest> =

            MessageBuilder
                .withPayload(kafkaRequest)
                .setHeader(KafkaHeaders.TOPIC, "testTopic")
                .build()
        kafkaTemplate.send(message)

    }


    fun publishOnUpsTrackingTopic(upsResponseDto: UpsResponseDto){
        val message: Message<UpsResponseDto> =
            MessageBuilder
                .withPayload(upsResponseDto)
                .setHeader(KafkaHeaders.TOPIC, "upsTrackingTopic")
                .build()
        kafkaTemplate.send(message)
    }


    fun senMessageViaEvent() {
        val eventHandler = GenericEventHandler(logger)
        val uri = "https://stream.wikimedia.org/v2/stream/recentchange"
        val eventSourceBuilder = EventSource.Builder(URI(uri))

        val builder =
            BackgroundEventSource.Builder(
                eventHandler,
                eventSourceBuilder
            )
        val eventSource = builder.build()
        eventSource.start()
        TimeUnit.MINUTES.sleep(10)

    }


    fun publishEarthQuake(earthQuakeDTO: EarthQuakeDTO) {
        val message: Message<EarthQuakeDTO> =
            MessageBuilder
                .withPayload(earthQuakeDTO)
                .setHeader(KafkaHeaders.TOPIC, "earthQuakeTopic")
                .build()
        kafkaTemplate.send(message)
    }


    fun broadCast(issDTO: IssDTO, topic: String) {
        val message: Message<IssDTO> =
            MessageBuilder
                .withPayload(issDTO)
                .setHeader(KafkaHeaders.TOPIC, topic)
                .build()
        kafkaTemplate.send(message)
    }

}