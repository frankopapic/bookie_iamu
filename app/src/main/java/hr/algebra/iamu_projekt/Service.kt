@file:Suppress("DEPRECATION")

package hr.algebra.iamu_projekt

import android.app.job.JobService
import android.content.Context
import android.content.Intent
import androidx.core.app.JobIntentService
import hr.algebra.iamu_projekt.API.Fetcher

private const val JOB_ID = 1

class Service: JobIntentService() {
    override fun onHandleWork(intent: Intent) {
        Fetcher(this).fetchItems()
    }
    companion object {
        fun enqueueWork(context: Context, intent: Intent) {
            enqueueWork(context, Service::class.java, JOB_ID, intent)
        }
    }
}
