package net.torbenvoltmer.ffis.android


import net.torbenvoltmer.ffis.common.state.timedstate.NoDataTimedState
import net.torbenvoltmer.ffis.common.state.timedstate.TimedState


/**
 * Created by torben on 03.08.17.
 */
object LocalStateManager {
    var localFlyingTimedState: TimedState = NoDataTimedState()

}