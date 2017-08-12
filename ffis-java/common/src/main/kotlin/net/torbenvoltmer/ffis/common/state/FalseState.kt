package net.torbenvoltmer.ffis.common.state

/**
 * Created by torben on 8/10/17.
 */
object FalseState : State {

    override fun accept(visitor: StateVisitor) {
        visitor.handle(this)
    }
}