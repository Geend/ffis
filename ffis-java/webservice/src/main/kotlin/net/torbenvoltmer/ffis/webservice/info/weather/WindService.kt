package net.torbenvoltmer.ffis.webservice.info.weather

import net.torbenvoltmer.ffis.webservice.config.LocationConfig
import org.springframework.stereotype.Service
import org.bitpipeline.lib.owm.OwmClient
import org.springframework.beans.factory.annotation.Autowired


/**
 * Created by torben on 4/27/17.
 */
@Service
class WindService @Autowired constructor(val locationConfig: LocationConfig) {
    fun  getWindInfo(): WindInfo {

        val owm = OwmClient()

        owm.setAPPID("7d81edcee50b6a34959b5e0e5fa622ec")
        owm.currentWeatherAtCity(locationConfig.latitude.toFloat(), locationConfig.longitudo.toFloat(),1)
        return WindInfo(0.0, 0.0)
    }
}