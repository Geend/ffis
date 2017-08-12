package net.torbenvoltmer.ffis.android

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.widget.TextView
import com.google.firebase.messaging.FirebaseMessaging

import android.view.MenuItem
import android.widget.Toast
import net.torbenvoltmer.ffis.common.state.FalseState
import net.torbenvoltmer.ffis.common.state.StateVisitor
import net.torbenvoltmer.ffis.common.state.TrueState
import net.torbenvoltmer.ffis.common.state.UndefinedState
import org.joda.time.format.DateTimeFormat


class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(net.torbenvoltmer.ffis.android.R.layout.activity_main)


        val mainToolbar = findViewById(R.id.main_toolbar) as Toolbar
        setSupportActionBar(mainToolbar)


        FirebaseMessaging.getInstance().subscribeToTopic("dev")
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

        val fmt = DateTimeFormat.forPattern("HH:mm dd.MM.yyyy")
        val dtStr = fmt.print(LocalStateManager.localFlyingTimedState.since)

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


        val state = findViewById(R.id.textview_state) as TextView
        val since = findViewById(R.id.textview_since) as TextView


        state.setText(title)
        since.setText(text)
    }
}
