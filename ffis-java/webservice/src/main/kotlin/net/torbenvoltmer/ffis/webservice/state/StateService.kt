package net.torbenvoltmer.ffis.webservice.state


import net.torbenvoltmer.ffis.common.state.ConcreteState
import net.torbenvoltmer.ffis.common.state.NoDataState
import net.torbenvoltmer.ffis.common.state.State

import net.torbenvoltmer.ffis.webservice.state.sunset.SunsetNotifyReciever
import net.torbenvoltmer.ffis.webservice.state.sunset.SunsetStateChangeTimer
import org.joda.time.DateTime
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

/**
 * Created by torben on 08.04.16.
 */


@Service
class StateService @Autowired constructor(sunsetStateChangeTimer: SunsetStateChangeTimer) : SunsetNotifyReciever {


    private var currentFlyingState: State = NoDataState()
    private var currentGrillingState: State = NoDataState()

    init {
        sunsetStateChangeTimer.receiver = this
    }

    fun getFlyingState(): State {
        return currentFlyingState
    }

    fun setFlying(state:Boolean){
        currentFlyingState = ConcreteState(state, DateTime.now())

    }

    fun setGrillingState(state: Boolean) {
        currentGrillingState = ConcreteState(state, DateTime.now())

    }

    fun getGrillingState(): State {
        return currentGrillingState;
    }

    override fun handleSunset() {
        currentFlyingState = ConcreteState(false, DateTime.now())
        currentGrillingState = ConcreteState(false, DateTime.now())
    }
}