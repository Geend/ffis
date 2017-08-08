package net.torbenvoltmer.ffis.android

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.View
import android.widget.TextView
import com.google.firebase.messaging.FirebaseMessaging

import android.view.MenuItem
import android.widget.Toast


class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(net.torbenvoltmer.ffis.android.R.layout.activity_main)


        val mainToolbar = findViewById(R.id.main_toolbar) as Toolbar
        setSupportActionBar(mainToolbar)


        FirebaseMessaging.getInstance().subscribeToTopic("haec")
        FfisRestClient.loadFlyingState()
    }

    override fun onResume() {
        super.onResume()
        // We shoudn't need to reload the value, but instead load from local cache (LocalStateManager). If the activity is not visible firebase messaging is used to refresh the local state
        setTextViewTexts()
    }



    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.mainmenu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.getItemId()) {

            R.id.action_refresh -> {

                FfisRestClient.loadFlyingState()

                setTextViewTexts()

                Toast.makeText(this, "Status aktualisiert", Toast.LENGTH_SHORT)
                        .show();
            }
            else -> {
            }
        }

        return true
    }


    fun setTextViewTexts(){
        val state = findViewById(R.id.textview_state) as TextView
        state.setText("Flugbetrieb: " + LocalStateManager.localFlyingState.state.toString())

        val since = findViewById(R.id.textview_since) as TextView
        since.setText("Seit " + LocalStateManager.localFlyingState.since.toString())
    }
}
