package net.torbenvoltmer.ffis.webservice.state.sunset

import java.time.LocalDateTime
import java.time.ZoneId
import java.util.Calendar
import java.util.Date
import java.util.Timer
import java.util.TimerTask
import com.luckycatlabs.sunrisesunset.SunriseSunsetCalculator
import com.luckycatlabs.sunrisesunset.dto.Location
import net.torbenvoltmer.ffis.webservice.config.LocationConfig
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
open class SunsetStateChangeTimer @Autowired constructor(locationConfig: LocationConfig) {


    private val timer = Timer()
    private val zone = ZoneId.of(locationConfig.zoneName)
    private val calculator: SunriseSunsetCalculator

    private val tomorrow: Calendar
        get() {
            val tom = Calendar.getInstance()
            tom.add(Calendar.DATE, 1)
            return tom
        }


    var receiver: SunsetNotifyReciever = EmptySunsetNotifyReciever();

    init {
        log.error("Test");
        val location = Location(locationConfig.latitude, locationConfig.longitudo)
        calculator = SunriseSunsetCalculator(location, zone.id)

        val now = LocalDateTime.now(zone)
        val sunset = getSunset(Calendar.getInstance())

        if (now.isBefore(sunset))
            setupTimer(sunset)
        else {
            setupTimer(getSunset(tomorrow))
        }

    }

    private fun setupTimer(time: LocalDateTime) {

        log.info("Setting sunset timer for ${time.toLocalDate()} ${time.toLocalTime()}")
        val instant = time.atZone(zone).toInstant()

        val res = Date.from(instant)

        timer.schedule(object : TimerTask() {
            override fun run() {
                log.info("It's sunset. Setting the local state to false now.")

                setupTimer(getSunset(tomorrow))
                receiver.handleSunset();
            }

        }, res)
    }

    private fun getSunset(date: Calendar): LocalDateTime {
        return LocalDateTime.ofInstant(calculator.getOfficialSunsetCalendarForDate(date).toInstant(),zone)

    }

    companion object {
        internal val log = LoggerFactory.getLogger(SunsetStateChangeTimer::class.java)
    }


}
