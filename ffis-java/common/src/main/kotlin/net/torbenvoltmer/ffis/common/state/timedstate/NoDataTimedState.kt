package net.torbenvoltmer.ffis.common.state.timedstate

import net.torbenvoltmer.ffis.common.state.State
import net.torbenvoltmer.ffis.common.state.UndefinedState
import org.joda.time.DateTime
import java.util.*

/**
 * Created by torben on 03.08.17.
 */
class NoDataTimedState : TimedState {

    override val since: Date
        get() = Date()

    override val state: State
        get() = UndefinedState
}