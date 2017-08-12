package net.torbenvoltmer.ffis.common.state.timedstate

import net.torbenvoltmer.ffis.common.state.State
import org.joda.time.DateTime

/**
 * Created by torben on 03.08.17.
 */
interface TimedState {

    val state: State
    val since: DateTime
}