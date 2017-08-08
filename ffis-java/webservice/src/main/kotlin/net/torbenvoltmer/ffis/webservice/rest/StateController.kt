package net.torbenvoltmer.ffis.webservice.rest

import net.torbenvoltmer.ffis.webservice.state.StateService
import net.torbenvoltmer.ffis.webservice.firebase.FirebaseAdminClient
import net.torbenvoltmer.ffis.webservice.firebase.FirebaseService

import net.torbenvoltmer.ffis.common.state.State
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
    fun getFlying(): State {
        return stateService.getFlyingState();
    }

    @org.springframework.web.bind.annotation.RequestMapping("/flying/set")
    fun setFlying(@org.springframework.web.bind.annotation.RequestParam(value="state") state:Boolean): State {
        stateService.setFlying(state);


        val mapper = ObjectMapper()
        mapper.registerModule(JodaModule())
        mapper.registerModule(KotlinModule())

        val json = mapper.writeValueAsString(stateService.getFlyingState())


        val data:MutableMap<String, String> = HashMap<String, String>()

        data.put("type", "flyingState")
        data.put("data", json)

        firebaseService.sendData("/topics/haec", data)


        return stateService.getFlyingState();
    }

    @org.springframework.web.bind.annotation.RequestMapping("/grilling/get")
    fun getGrilling(): State {
        return stateService.getGrillingState();
    }

    @org.springframework.web.bind.annotation.RequestMapping("/grilling/set")
    fun setGrilling(@org.springframework.web.bind.annotation.RequestParam(value="state") state:Boolean): State {
        stateService.setGrillingState(state);
        return stateService.getGrillingState();
    }

}