package net.torbenvoltmer.ffis.webservice.firebase

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider
import com.fasterxml.jackson.module.kotlin.KotlinModule
import com.github.kittinunf.fuel.Fuel
import com.github.kittinunf.fuel.core.FuelManager
import java.nio.charset.Charset

import java.util.*


open class FirebaseAdminClient(key:String){


    private val url:String = "https://fcm.googleapis.com/fcm/send"
    private val header:MutableMap<String,String> = HashMap()

    private val jsonMapper = ObjectMapper().registerModule(KotlinModule())


    init {
        header.put("Content-Type", "application/json")
        header.put("Authorization", "key=" + key)

        FuelManager.instance.basePath = url
        FuelManager.instance.baseHeaders = header
    }



    fun sendData(to:String, data:Map<String,String>){

        val message:DownstreamHttpMessage = DownstreamHttpMessage()

        message.to = to
        message.data = data


        val filters = SimpleFilterProvider().addFilter("downstreamHttpMessageFilter", message.filter)
        val jsonData:String = jsonMapper.setFilterProvider(filters).writeValueAsString(message)

        Fuel.post("").body(jsonData).response()

        //TODO: Error handling
        val t:String = String(Fuel.post("").body(jsonData).response().second.data, Charset.defaultCharset())

    }

    fun sendNotification(){

    }


}

