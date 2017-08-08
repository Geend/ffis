package net.torbenvoltmer.ffis.webservice.config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Configuration

@Configuration
@ConfigurationProperties(prefix = "ffis.firebase")
open class FirebaseConfig {

    lateinit var key: String

}