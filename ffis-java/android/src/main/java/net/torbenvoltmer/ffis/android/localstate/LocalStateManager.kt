package net.torbenvoltmer.ffis.android.localstate


import net.torbenvoltmer.ffis.android.FfisRestClient
import net.torbenvoltmer.ffis.common.state.timedstate.NoDataTimedState
import net.torbenvoltmer.ffis.common.state.timedstate.TimedState


/**
 * Created by torben on 03.08.17.
 */
object LocalStateManager : LocalStateObservee() {
    var localFlyingTimedState: TimedState = NoDataTimedState()
        set(value){
            field = value
            notifyObservers()
        }



    fun refreshLocalFlyingState(){
        //We can't actually asign the value here, because it's loaded asynchronously
        FfisRestClient.loadFlyingState()

    }

}