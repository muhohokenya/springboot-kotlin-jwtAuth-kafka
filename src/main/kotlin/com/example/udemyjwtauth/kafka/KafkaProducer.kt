package com.example.udemyjwtauth.kafka

import com.example.udemyjwtauth.dto.KafkaRequest
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.kafka.support.KafkaHeaders
import org.springframework.messaging.Message
import org.springframework.messaging.support.MessageBuilder
import org.springframework.stereotype.Service

@Service
class KafkaProducer(
    val kafkaTemplate: KafkaTemplate<String,String>
) {
    fun sendMessage(kafkaRequest: KafkaRequest){
        val message:Message<KafkaRequest> =

            MessageBuilder
            .withPayload(kafkaRequest)
            .setHeader(KafkaHeaders.TOPIC,"testTopic")
            .build()
        kafkaTemplate.send(message)

    }

}