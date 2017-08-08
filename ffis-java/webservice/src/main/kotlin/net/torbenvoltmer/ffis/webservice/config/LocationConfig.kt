package net.torbenvoltmer.ffis.webservice.config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Configuration

/**
 * Created by torben on 29.03.16.
 */
@Configuration
@ConfigurationProperties(prefix = "opind.location")
open class LocationConfig {

    lateinit var zoneName: String;
    lateinit var longitudo: String;
    lateinit var latitude: String;

}