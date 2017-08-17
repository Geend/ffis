package net.torbenvoltmer.ffis.android.localstate


import net.torbenvoltmer.ffis.android.FfisRestClient
import net.torbenvoltmer.ffis.common.state.timedstate.NoDataTimedState
import net.torbenvoltmer.ffis.common.state.timedstate.TimedState


/**
 * Created by torben on 03.08.17.
 */
object LocalStateManager : LocalStateObservee() {

    //TODO: Consider retraining write access to this attribute to certain classes
    var localFlyingTimedState: TimedState = NoDataTimedState()
        set(value){
            field = value
            notifyObservers()
        }



    fun refreshLocalFlyingState(){
        //We can't actually assign the value here, because it's loaded asynchronously
        FfisRestClient.loadFlyingState()

    }

}