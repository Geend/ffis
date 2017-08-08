package net.torbenvoltmer.ffis.webservice.firebase

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.ser.BeanPropertyFilter
import com.fasterxml.jackson.module.kotlin.KotlinModule
import org.junit.Test

import org.junit.Assert.*
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider
import com.fasterxml.jackson.databind.ser.FilterProvider
import com.fasterxml.jackson.databind.ser.PropertyFilter
import java.util.*


class FirebaseAdminClientTest {
    @Test
    fun test1() {

        var client:FirebaseAdminClient = FirebaseAdminClient("AAAAkIkoy3E:APA91bHx7qbr8R3P0CJo8LfcdqoDJh6HBdOCKveKyKy3s_5_htLkEx0MCkyLQ7lUHnyyMplbR48LB7EV60UVzw93y_KoS36SMmWxnpNkh2Y2ZFnfe9vqJNgqwrw5E-rqNihGAyMTZ1zm")

        //client.test()

        val data:MutableMap<String, String> = HashMap<String, String>()
        data.put("d1", "123")
        client.sendData("/topics/test", data)
    }


    @Test
    fun test2(){
        /*
        val message:DownstreamHttpMessage = DownstreamHttpMessage()

        message.to = "test"


        val data:MutableMap<String, String> = HashMap<String, String>()
        data.put("d1", "123")
        message.data = data



        val mapper = ObjectMapper().registerModule(KotlinModule())


        val filters = SimpleFilterProvider().addFilter("myFilter", message.filter)
        val result:String = mapper.setFilterProvider(filters).writeValueAsString(message)

        System.out.println(result)
        */
    }

}