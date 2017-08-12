package net.torbenvoltmer.ffis.webservice.state


import net.torbenvoltmer.ffis.common.state.FalseState
import net.torbenvoltmer.ffis.common.state.TrueState
import net.torbenvoltmer.ffis.common.state.timedstate.ConcreteTimedState
import net.torbenvoltmer.ffis.common.state.timedstate.NoDataTimedState
import net.torbenvoltmer.ffis.common.state.timedstate.TimedState

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


    private var currentFlyingTimedState: TimedState = NoDataTimedState()
    private var currentGrillingTimedState: TimedState = NoDataTimedState()

    init {
        sunsetStateChangeTimer.receiver = this
    }

    fun getFlyingState(): TimedState {
        return currentFlyingTimedState
    }

    fun setFlying(state:Boolean){

        if(state) {
            currentFlyingTimedState = ConcreteTimedState(TrueState, DateTime.now())
        }
        else{
            currentFlyingTimedState = ConcreteTimedState(FalseState, DateTime.now())
        }
    }

    fun setGrillingState(state: Boolean) {
        if(state) {
            currentGrillingTimedState = ConcreteTimedState(TrueState, DateTime.now())
        }
        else{
            currentGrillingTimedState = ConcreteTimedState(FalseState, DateTime.now())
        }
    }

    fun getGrillingState(): TimedState {
        return currentGrillingTimedState;
    }

    override fun handleSunset() {
        currentFlyingTimedState = ConcreteTimedState(FalseState, DateTime.now())
        currentGrillingTimedState = ConcreteTimedState(FalseState, DateTime.now())
    }
}