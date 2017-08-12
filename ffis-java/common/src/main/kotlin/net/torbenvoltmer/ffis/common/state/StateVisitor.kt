package net.torbenvoltmer.ffis.common.state

/**
 * Created by torben on 8/10/17.
 */
interface StateVisitor {
    fun handle(arg: TrueState)
    fun handle(arg: FalseState)
    fun handle(arg: UndefinedState)


}