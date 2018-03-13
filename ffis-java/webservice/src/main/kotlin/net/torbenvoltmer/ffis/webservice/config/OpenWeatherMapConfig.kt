package net.torbenvoltmer.ffis.webservice.config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Configuration

@Configuration
@ConfigurationProperties(prefix = "ffis.openweathermap")
open class OpenWeatherMapConfig {

    /**
     * Key for firebase connection
     */
    lateinit var key: String

}