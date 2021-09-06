package hr.algebra.iamu_projekt

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import hr.algebra.iamu_projekt.R.layout.activity_splash
import hr.algebra.iamu_projekt.FRAMEWORK.applyAnimation
import hr.algebra.iamu_projekt.FRAMEWORK.getBooleanPreference
import hr.algebra.iamu_projekt.FRAMEWORK.isOnline
import hr.algebra.iamu_projekt.FRAMEWORK.startActivity
import kotlinx.android.synthetic.main.activity_splash.*
import java.util.concurrent.DelayQueue

private const val DELAY : Long = 3000
const val DATA_IMPORTED = "hr.algebra.iamu_projekt.data_imported"


@Suppress("DEPRECATION")
class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        startAnimations()
        redirect()

    }


    private fun startAnimations() {
        ivLogo.applyAnimation(R.anim.heartbeat)
        ivSpinner.applyAnimation(R.anim.spin)
        val decorView = window.decorView
        decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_FULLSCREEN
                or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY)
    }

    private fun redirect() {
        if (getBooleanPreference(DATA_IMPORTED)) {
            Handler(Looper.getMainLooper()).postDelayed(
                {startActivity<HostActivity>()},
                DELAY
            )
        } else {
            if (isOnline()) {
                // start service
                Intent(this, Service::class.java).apply {
                    Service.enqueueWork(this@SplashActivity, this)
                }
            }else{
                Toast.makeText(this, getString(R.string.connectToInternet), Toast.LENGTH_SHORT).show()
                finish()
            }
        }
    }
}