package com.example.udemyjwtauth.controllers

import com.example.udemyjwtauth.dto.FedexTokenRequest
import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.ObjectMapper
import org.slf4j.Logger
import org.springframework.http.*
import org.springframework.util.LinkedMultiValueMap
import org.springframework.util.MultiValueMap
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.client.RestTemplate
import org.springframework.web.reactive.function.BodyInserters
import org.springframework.web.reactive.function.client.WebClient
import java.net.URI


@RestController
@RequestMapping("api/v1/fedex")
class FedexTrackController(
    val logger: Logger
) {
    val webClient = WebClient.create()


    @PostMapping("iss")
    fun sendRequest():ResponseEntity<Any>{
        val urlEndpoint = "http://api.open-notify.org/iss-now.json"
        val data = webClient.get()
            .uri(urlEndpoint)
            .retrieve().bodyToMono(String::class.java).block()

        logger.info(data)
        return ResponseEntity.ok(data)
    }



    private fun getToken(): ResponseEntity<String> {
        val bodyValues: MultiValueMap<String, String> = LinkedMultiValueMap()
        bodyValues.add("client_id","l7f88fdd0c920f43358c9df921b676b962")
        bodyValues.add("client_secret","d4d51d845dda47828a86aa3f2237f99e")
        bodyValues.add("grant_type","client_credentials")
        val response = webClient.post()
            .uri(URI("https://apis.fedex.com/oauth/token"))
            .contentType(MediaType.APPLICATION_FORM_URLENCODED)
            .accept(MediaType.APPLICATION_JSON)
            .body(BodyInserters.fromFormData(bodyValues))
            .retrieve()
            .bodyToMono(String::class.java)
            .block()

        val jsonMapper = ObjectMapper()
        val root: JsonNode = jsonMapper.readTree(response)
        val accessToken = root.path("access_token")
        return ResponseEntity.ok(accessToken.toString())
    }

    @PostMapping("track")
    fun trackOrder(): ResponseEntity<String> {
        val token = getToken()

        /*
        * {"includeDetailedScans":false,
        * "trackingInfo":[
        * {"trackingNumberInfo":
        * {"trackingNumber":"393904902106"
        * }
        * }]}
        * */
        val trackBodyValues: MultiValueMap<String, String> = LinkedMultiValueMap()
        trackBodyValues.add("includeDetailedScans", false.toString())
        trackBodyValues.add("trackingInfo",
            trackBodyValues.add("trackingNumberInfo",
                trackBodyValues.add("trackingNumber","394969335046").toString()
            ).toString())

       val trackResponse =  webClient.post()
            .uri(URI("https://apis.fedex.com/track/v1/trackingnumbers"))
            .header("Authorization", "Bearer $token")
            .contentType(MediaType.APPLICATION_FORM_URLENCODED)
            .accept(MediaType.APPLICATION_JSON)
            .body(BodyInserters.fromValue(trackBodyValues))
            .retrieve()
            .bodyToMono(String::class.java)
            .block()

        return ResponseEntity.ok(trackResponse.toString())
    }



    @PostMapping("")
    fun trackOrders(){
        val fedexTokenRequest = FedexTokenRequest(
            "l7f88fdd0c920f43358c9df921b676b962",
            "d4d51d845dda47828a86aa3f2237f99e",
            "client_credentials",
        )
        val headers = HttpHeaders()
//        headers.set("Content-Type","application/x-www-form-urlencoded")
        headers.contentType = MediaType.APPLICATION_FORM_URLENCODED
        val entity  = HttpEntity<FedexTokenRequest>(fedexTokenRequest,headers)
        val restTemplate = RestTemplate()
        val jsonMapper = ObjectMapper()
        val response = restTemplate.exchange(
            "https://apis.fedex.com/oauth/token",
            HttpMethod.POST,
            entity,
            FedexTokenRequest::class.javaObjectType)
         jsonMapper.readTree(response.toString())


    }

}