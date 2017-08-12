package net.torbenvoltmer.ffis.webservice.rest

import net.torbenvoltmer.ffis.webservice.state.StateService
import net.torbenvoltmer.ffis.webservice.firebase.FirebaseService

import net.torbenvoltmer.ffis.common.state.timedstate.TimedState
import java.util.*
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.datatype.joda.JodaModule
import com.fasterxml.jackson.module.kotlin.KotlinModule


/**
 * Created by torben on 28.03.16.
 */

@org.springframework.web.bind.annotation.RestController
class StateController @org.springframework.beans.factory.annotation.Autowired constructor(var stateService: StateService, var firebaseService: FirebaseService){

    @org.springframework.web.bind.annotation.RequestMapping("/flying/get")
    fun getFlying(): TimedState {
        return stateService.getFlyingState();
    }

    @org.springframework.web.bind.annotation.RequestMapping("/flying/set")
    fun setFlying(@org.springframework.web.bind.annotation.RequestParam(value="state") state:Boolean): TimedState {
        stateService.setFlying(state);


        val mapper = ObjectMapper()
        mapper.registerModule(JodaModule())
        mapper.registerModule(KotlinModule())
        mapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);

        val json = mapper.writeValueAsString(stateService.getFlyingState())


        val data:MutableMap<String, String> = HashMap<String, String>()

        data.put("type", "flyingState")
        data.put("data", json)

        firebaseService.sendData("/topics/dev", data)


        return stateService.getFlyingState();
    }

    @org.springframework.web.bind.annotation.RequestMapping("/grilling/get")
    fun getGrilling(): TimedState {
        return stateService.getGrillingState();
    }

    @org.springframework.web.bind.annotation.RequestMapping("/grilling/set")
    fun setGrilling(@org.springframework.web.bind.annotation.RequestParam(value="state") state:Boolean): TimedState {
        stateService.setGrillingState(state);
        return stateService.getGrillingState();
    }

}