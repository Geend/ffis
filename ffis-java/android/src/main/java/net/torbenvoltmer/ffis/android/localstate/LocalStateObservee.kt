package net.torbenvoltmer.ffis.android.localstate

import java.util.*

/**
 * Created by torben on 8/17/17.
 */
abstract class LocalStateObservee {

    val observers: MutableList<LocalStateObserver> = LinkedList()

    fun addObserver(observer: LocalStateObserver){
        observers.add(observer)
    }

    fun removeObserver(observer: LocalStateObserver){
        observers.remove(observer)
    }

    fun notifyObservers(){
        observers.forEach{ it.handleLocalStateRefresh() }
    }

}