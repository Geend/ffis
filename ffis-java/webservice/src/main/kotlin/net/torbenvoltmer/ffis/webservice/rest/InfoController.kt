package net.torbenvoltmer.ffis.webservice.rest

import net.torbenvoltmer.ffis.webservice.info.weather.WindInfo
import net.torbenvoltmer.ffis.webservice.info.weather.WeatherService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

/**
 * Created by torben on 28.03.16.
 */

@RestController
class InfoController @Autowired constructor(var windService: WeatherService){

    @RequestMapping("/weather/wind")
    fun getWindInfo(): WindInfo {
        return windService.getWindInfo();
    }
}

