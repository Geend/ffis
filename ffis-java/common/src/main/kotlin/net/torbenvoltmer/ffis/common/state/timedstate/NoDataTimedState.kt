package net.torbenvoltmer.ffis.common.state.timedstate

import net.torbenvoltmer.ffis.common.state.State
import net.torbenvoltmer.ffis.common.state.UndefinedState
import org.joda.time.DateTime

/**
 * Created by torben on 03.08.17.
 */
class NoDataTimedState : TimedState {

    override val since: DateTime
        get() = DateTime.now()

    override val state: State
        get() = UndefinedState
}