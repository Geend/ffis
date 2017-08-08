package net.torbenvoltmer.ffis.common.state

import net.torbenvoltmer.ffis.common.state.State
import org.joda.time.DateTime

/**
 * Created by torben on 03.08.17.
 */
class NoDataState : State {

    override val since: DateTime
        get() = DateTime.now()

    override val state: Boolean
        get() = false
}