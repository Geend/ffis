package net.torbenvoltmer.ffis.webservice.rest

import net.torbenvoltmer.ffis.webservice.info.weather.WindInfo
import net.torbenvoltmer.ffis.webservice.info.weather.WindService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

/**
 * Created by torben on 28.03.16.
 */

@RestController
class InfoController @Autowired constructor(var windService: WindService){

    @RequestMapping("/weather/wind")
    fun getWindInfo(): WindInfo {
        return windService.getWindInfo();
        //http://api.openweathermap.org/data/2.5/weather?lat=52.42357822&lon=9.76456169&appid=18c508543699146905bcdd3d427ba09d
        //http://api.openweathermap.org/data/2.5/weather?lat=52.42357822&lon=9.76456169&appid=7d81edcee50b6a34959b5e0e5fa622ec
    }



}

