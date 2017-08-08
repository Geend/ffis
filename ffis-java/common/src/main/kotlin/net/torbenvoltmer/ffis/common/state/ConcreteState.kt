package net.torbenvoltmer.ffis.common.state

import net.torbenvoltmer.ffis.common.state.State
import org.joda.time.DateTime

/**
 * Created by torben on 03.08.17.
 */
class ConcreteState(override val state:Boolean, override val since:DateTime) : State {



}