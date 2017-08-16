package net.torbenvoltmer.ffis.android

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.messaging.FirebaseMessaging
import net.torbenvoltmer.ffis.android.localstate.LocalStateManager
import net.torbenvoltmer.ffis.android.localstate.LocalStateObservee
import net.torbenvoltmer.ffis.android.localstate.LocalStateObserver
import net.torbenvoltmer.ffis.common.state.FalseState
import net.torbenvoltmer.ffis.common.state.StateVisitor
import net.torbenvoltmer.ffis.common.state.TrueState
import net.torbenvoltmer.ffis.common.state.UndefinedState
import java.text.SimpleDateFormat


class MainActivity : LocalStateObserver, AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(net.torbenvoltmer.ffis.android.R.layout.activity_main)


        val mainToolbar = findViewById<Toolbar>(R.id.main_toolbar)
        setSupportActionBar(mainToolbar)


        FirebaseMessaging.getInstance().subscribeToTopic("dev")

        LocalStateManager.addObserver(this)
        LocalStateManager.refreshLocalFlyingState()

    }

    override fun onResume() {
        super.onResume()
        // We shoudn't need to reload the value, but instead load from local cache (LocalStateManager). If the activity is not visible firebase messaging is used to refresh the local state
        setTextViewTexts()
    }

    override fun handleLocalStateRefresh() {
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

                LocalStateManager.refreshLocalFlyingState()


                setTextViewTexts()

                Toast.makeText(this, getString(R.string.stateRefreshed), Toast.LENGTH_SHORT)
                        .show();
            }
            else -> {
            }
        }

        return true
    }


    fun setTextViewTexts(){


        val fmt = SimpleDateFormat("HH:mm dd.MM.yyyy")
        val dtStr = fmt.format(LocalStateManager.localFlyingTimedState.since)

        var title:String = ""
        var text:String = ""

        LocalStateManager.localFlyingTimedState.state.accept(object : StateVisitor {
            override fun handle(arg: TrueState) {
                title =  getString(R.string.flying);
                text = getString(R.string.flying_since,  dtStr)
            }

            override fun handle(arg: FalseState) {
                title = getString(R.string.not_fling);
                text = getString(R.string.not_flying_since,  dtStr)
            }

            override fun handle(arg: UndefinedState) {
                title = getString(R.string.unknown_fling);
                text = getString(R.string.unknown_flying_since,  dtStr)
            }
        })


        val state = findViewById<TextView>(R.id.textview_state)
        val since = findViewById<TextView>(R.id.textview_since)


        state.setText(title)
        since.setText(text)
    }
}
