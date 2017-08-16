package net.torbenvoltmer.ffis.android

import com.loopj.android.http.AsyncHttpClient
import com.loopj.android.http.AsyncHttpResponseHandler
import cz.msebera.android.httpclient.Header
import java.nio.charset.Charset

/**
 * Created by torben on 04.08.17.
 */
object FfisRestClient {

    fun loadFlyingState(){
        val client = AsyncHttpClient()

        //TODO: Make URL a user setting
        //TODO Error handling
        //client.get("http://state.haec.de/flying/get", object : AsyncHttpResponseHandler() {
        client.get("http://state.haec.de/flying/get", object : AsyncHttpResponseHandler() {


            override fun onStart() {
            }

            override fun onSuccess(statusCode: Int, headers: Array<Header>, response: ByteArray) {
                val json:String = String(response, Charset.defaultCharset())

                //TODO: Fix timezones
                val newState = FfisJsonDecoder.timedState(json)

                LocalStateManager.localFlyingTimedState = newState
            }

            override fun onFailure(statusCode: Int, headers: Array<Header>, errorResponse: ByteArray, e: Throwable) {
            }

            override fun onRetry(retryNo: Int) {
            }
        })
    }
}