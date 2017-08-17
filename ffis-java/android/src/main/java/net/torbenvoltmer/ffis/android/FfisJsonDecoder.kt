package net.torbenvoltmer.ffis.android

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.datatype.joda.JodaModule
import com.fasterxml.jackson.module.kotlin.KotlinModule
import net.torbenvoltmer.ffis.common.state.timedstate.ConcreteTimedState
import net.torbenvoltmer.ffis.common.state.timedstate.TimedState

/**
 * Created by torben on 8/12/17.
 */
object FfisJsonDecoder {

    //TODO: Consider moving the jackson ObjectMapper to the common project, because it is used in exactly the same configureation in the webservice project
    val mapper:ObjectMapper = ObjectMapper();

    init {
        mapper.registerModule(KotlinModule())
        mapper.registerModule(JodaModule())
        mapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL)
    }


    fun timedState(json:String):TimedState{
       return mapper.readValue(json, ConcreteTimedState::class.java)
    }


}