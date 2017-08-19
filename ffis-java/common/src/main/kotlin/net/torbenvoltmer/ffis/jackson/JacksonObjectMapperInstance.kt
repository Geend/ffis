package net.torbenvoltmer.ffis.jackson

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.module.kotlin.KotlinModule

/**
 * Created by torben on 17.08.17.
 */
object JacksonObjectMapperInstance {


    val mapper:ObjectMapper = ObjectMapper();

    init {
        mapper.registerModule(KotlinModule())
        mapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL)
        mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS , false)
    }

}