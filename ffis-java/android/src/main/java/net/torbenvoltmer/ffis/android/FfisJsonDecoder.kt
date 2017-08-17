package net.torbenvoltmer.ffis.android

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.datatype.joda.JodaModule
import com.fasterxml.jackson.module.kotlin.KotlinModule
import net.torbenvoltmer.ffis.common.state.timedstate.ConcreteTimedState
import net.torbenvoltmer.ffis.common.state.timedstate.TimedState
import net.torbenvoltmer.ffis.jackson.JacksonObjectMapperInstance

/**
 * Created by torben on 8/12/17.
 */
object FfisJsonDecoder {

    fun timedState(json:String):TimedState{
       return JacksonObjectMapperInstance.mapper.readValue(json, ConcreteTimedState::class.java)
    }


}