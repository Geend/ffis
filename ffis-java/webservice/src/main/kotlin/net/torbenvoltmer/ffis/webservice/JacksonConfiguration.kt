package net.torbenvoltmer.ffis.webservice

import org.springframework.context.annotation.Configuration
import com.fasterxml.jackson.databind.MapperFeature
import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.datatype.joda.JodaModule
import com.fasterxml.jackson.module.kotlin.KotlinModule
import net.torbenvoltmer.ffis.jackson.JacksonObjectMapperInstance
import net.torbenvoltmer.ffis.webservice.config.LocationConfig
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import java.text.DateFormat
import java.util.TimeZone
import java.text.SimpleDateFormat
import java.time.ZoneId


@Configuration
open class JacksonConfiguration {

    @Autowired
    lateinit var locationConfig:LocationConfig

    @Bean
    open fun objectMapper(): ObjectMapper {
        return  JacksonObjectMapperInstance.mapper
    }
}