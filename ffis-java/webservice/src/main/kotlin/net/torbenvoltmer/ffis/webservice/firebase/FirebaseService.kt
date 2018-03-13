package net.torbenvoltmer.ffis.webservice.firebase

import net.torbenvoltmer.ffis.webservice.config.FirebaseConfig
import net.torbenvoltmer.ffis.webservice.config.LocationConfig
import net.torbenvoltmer.ffis.webservice.info.weather.WindInfo
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class FirebaseService @Autowired constructor(val firebaseConfig: FirebaseConfig) {

    private val client:FirebaseAdminClient = FirebaseAdminClient(firebaseConfig.key)

    fun  sendData(s: String, data: MutableMap<String, String>) {
        client.sendData(s, data)

    }
}