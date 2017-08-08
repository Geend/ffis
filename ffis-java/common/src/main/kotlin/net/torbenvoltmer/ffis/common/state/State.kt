package net.torbenvoltmer.ffis.common.state

import org.joda.time.DateTime

/**
 * Created by torben on 03.08.17.
 */
interface State {

    val state:Boolean
    val since: DateTime
}