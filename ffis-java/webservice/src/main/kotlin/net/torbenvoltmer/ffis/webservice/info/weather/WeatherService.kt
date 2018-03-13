package net.torbenvoltmer.ffis.webservice.info.weather

import net.torbenvoltmer.ffis.webservice.config.LocationConfig
import org.springframework.stereotype.Service
import org.springframework.beans.factory.annotation.Autowired
import net.aksingh.owmjapis.core.OWM
import net.aksingh.owmjapis.model.CurrentWeather
import net.torbenvoltmer.ffis.webservice.config.OpenWeatherMapConfig


/**
 * Created by torben on 4/27/17.
 */
@Service
class WeatherService @Autowired constructor(val locationConfig: LocationConfig, val openWeatherMapConfig: OpenWeatherMapConfig) {


    private val owm = OWM(openWeatherMapConfig.key)


    fun  getWindInfo(): WindInfo {


        val result=owm.currentWeatherByCoords(locationConfig.latitude.toDouble(), locationConfig.longitudo.toDouble())
        val wind = result.windData

        if(wind != null) {

            val windSpeed = wind.speed
            val windDirection =  wind.degree


            return WindInfo(windSpeed!!, windDirection!!)
        }
        else
        {
            throw RuntimeException("Wind data not available")
        }
    }
}