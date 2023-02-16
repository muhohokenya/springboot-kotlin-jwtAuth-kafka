package com.example.udemyjwtauth.configs

import com.example.udemyjwtauth.dto.KafkaRequest
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Service

@Service
class KafkaConsumer {

    @KafkaListener(topics = ["testTopic"], groupId = "myGroup")
    fun consume(kafkaRequest: KafkaRequest){
        println(kafkaRequest)
    }
}