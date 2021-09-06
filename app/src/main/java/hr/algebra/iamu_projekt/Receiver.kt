package hr.algebra.iamu_projekt

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import hr.algebra.iamu_projekt.FRAMEWORK.setBooleanPreference
import hr.algebra.iamu_projekt.FRAMEWORK.startActivity

class Receiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        context.setBooleanPreference(DATA_IMPORTED, true)
        context.startActivity<HostActivity>()
    }
}