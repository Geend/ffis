package net.torbenvoltmer.ffis.webservice.config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Configuration

/**
 * Created by torben on 28.03.16.
 */
@Configuration
@ConfigurationProperties(prefix = "ffis.auth")
open class UserConfig {

    lateinit var user: String;
    lateinit var password: String;

}