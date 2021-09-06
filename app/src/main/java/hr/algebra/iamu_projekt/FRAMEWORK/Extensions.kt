package hr.algebra.iamu_projekt.FRAMEWORK

import android.app.Activity
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.view.View
import android.view.animation.AnimationUtils
import androidx.annotation.RequiresApi
import androidx.preference.PreferenceManager
import hr.algebra.iamu_projekt.PROVIDER_CONTENT_URI
import hr.algebra.iamu_projekt.MODEL.BookItem

fun View.applyAnimation(resourceId: Int) = startAnimation(AnimationUtils.loadAnimation(context,resourceId))

inline fun <reified T : Activity> Context.startActivity() = Intent(this, T::class.java).apply{
    addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
    startActivity(this)
}
inline fun <reified T : Activity> Context.startActivity(key: String, value: Int) = Intent(this, T::class.java).apply{
    putExtra(key, value)
    addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
    startActivity(this)
}
inline fun<reified T: BroadcastReceiver> Context.sendBroadcast() = sendBroadcast(Intent(this, T::class.java))

fun Context.setBooleanPreference(key: String, value: Boolean) =
    PreferenceManager.getDefaultSharedPreferences(this)
        .edit()
        .putBoolean(key, value)
        .apply()

fun Context.getBooleanPreference(key: String) : Boolean
        = PreferenceManager.getDefaultSharedPreferences(this).getBoolean(key, false)

fun Context.isOnline() : Boolean{
    val connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val network = connectivityManager.activeNetwork
    if (network!=null){
        val networkCapabilities = connectivityManager.getNetworkCapabilities(network)
        if (network!=null){
            if (networkCapabilities != null) {
                return networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) || networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)
            }
        }
    }

    return false
}

fun Context.fetchItems() : MutableList<BookItem>{
    val items = mutableListOf<BookItem>()
    val cursor = contentResolver?.query(PROVIDER_CONTENT_URI, null, null, null, null)

    if (cursor != null){
        while (cursor.moveToNext()){
            items.add(BookItem(
                    cursor.getLong(cursor.getColumnIndex(BookItem::_id.name)),
                    cursor.getString(cursor.getColumnIndex(BookItem::title.name)),
                    cursor.getString(cursor.getColumnIndex(BookItem::author.name)),
                    cursor.getInt(cursor.getColumnIndex(BookItem::rating.name)),
                    cursor.getInt(cursor.getColumnIndex(BookItem::pages.name)),
                    cursor.getInt(cursor.getColumnIndex(BookItem::published.name)),
                    cursor.getString(cursor.getColumnIndex(BookItem::description.name)),
                    cursor.getString(cursor.getColumnIndex(BookItem::cover.name)),
                    cursor.getString(cursor.getColumnIndex(BookItem::download.name)),
            ))
        }
    }
    return items
}