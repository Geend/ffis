package net.torbenvoltmer.ffis.android

import android.widget.TextView
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.datatype.joda.JodaModule
import com.fasterxml.jackson.module.kotlin.KotlinModule
import com.loopj.android.http.AsyncHttpClient
import com.loopj.android.http.AsyncHttpResponseHandler
import cz.msebera.android.httpclient.Header
import net.torbenvoltmer.ffis.common.state.ConcreteState
import java.nio.charset.Charset

/**
 * Created by torben on 04.08.17.
 */
object FfisRestClient {

    fun loadFlyingState(){
        val client = AsyncHttpClient()

        //TODO: Make URL a user setting
        //TODO Error handling
        client.get("http://state.haec.de/flying/get", object : AsyncHttpResponseHandler() {


            override fun onStart() {
            }

            override fun onSuccess(statusCode: Int, headers: Array<Header>, response: ByteArray) {
                val json:String = String(response, Charset.defaultCharset())


                val mapper = ObjectMapper().registerModule(KotlinModule())
                mapper.registerModule(JodaModule())

                //TODO: Check if datetime is handeld correctly
                val newState = mapper.readValue(json, ConcreteState::class.java)

                LocalStateManager.localFlyingState = newState
            }

            override fun onFailure(statusCode: Int, headers: Array<Header>, errorResponse: ByteArray, e: Throwable) {
            }

            override fun onRetry(retryNo: Int) {
            }
        })
    }
}