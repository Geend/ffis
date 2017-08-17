package net.torbenvoltmer.ffis.jackson

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.KotlinModule

/**
 * Created by torben on 17.08.17.
 */
object JacksonObjectMapperInstance {

    //TODO: Use this instance for the spring boot webservice
    val mapper:ObjectMapper = ObjectMapper();

    init {
        mapper.registerModule(KotlinModule())
        mapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL)
    }

}