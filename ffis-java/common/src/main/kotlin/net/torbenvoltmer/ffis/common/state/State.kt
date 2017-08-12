package net.torbenvoltmer.ffis.common.state

/**
 * Created by torben on 8/10/17.
 */
interface State {

    fun accept(visitor: StateVisitor)
}