package net.torbenvoltmer.ffis.webservice.firebase

import com.fasterxml.jackson.annotation.JsonFilter
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter
import java.util.*


@JsonFilter("downstreamHttpMessageFilter")
class DownstreamHttpMessage {

    val filter: SimpleBeanPropertyFilter
        get() = SimpleBeanPropertyFilter.filterOutAllExcept(filterAttributes)


    private val filterAttributes: MutableSet<String> = HashSet<String>()



    //Targets
    var to: String = ""
        set(value) {
            field = value
            filterAttributes.add("to")
        }

    var registration_ids: List<String> = ArrayList<String>()
        set(value) {
            field = value
            filterAttributes.add("registration_ids")
        }

    var condition: String = ""
        set(value) {
            field = value
            filterAttributes.add("condition")
        }

    //Options
    var collapse_key: String = ""
        set(value) {
            field = value
            filterAttributes.add("collapse_key")
        }

    var priority: String = ""
        set(value) {
            field = value
            filterAttributes.add("priority")
        }

    var content_available: Boolean = false
        set(value) {
            field = value
            filterAttributes.add("content_available")
        }

    var mutable_content: Boolean = false
        set(value) {
            field = value
            filterAttributes.add("mutable_content")
        }

    var time_to_live: Number = 0
        set(value) {
            field = value
            filterAttributes.add("time_to_live")
        }

    var restricted_package_name: String = ""
        set(value) {
            field = value
            filterAttributes.add("restricted_package_name")
        }

    var dry_run: Boolean = false
        set(value) {
            field = value
            filterAttributes.add("dry_run")
        }

    //Payload
    var data: Map<String, String> = HashMap<String, String>()
        set(value) {
            field = value
            filterAttributes.add("data")
        }


}