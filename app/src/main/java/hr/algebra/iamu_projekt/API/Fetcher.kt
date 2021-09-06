package hr.algebra.iamu_projekt.API

import android.content.ContentValues
import android.content.Context
import android.util.Log
import hr.algebra.iamu_projekt.FRAMEWORK.sendBroadcast
import hr.algebra.iamu_projekt.MODEL.BookItem
import hr.algebra.iamu_projekt.PROVIDER_CONTENT_URI
import hr.algebra.iamu_projekt.Receiver
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Fetcher(private val context: Context) {

    private var jsonApi : JsonAPI

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl(URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        jsonApi = retrofit.create(JsonAPI::class.java)
    }

    fun fetchItems() {
        val request = jsonApi.fetchItems()
        request.enqueue(object: Callback<List<Item>> {
            override fun onResponse(
                call: Call<List<Item>>,
                response: Response<List<Item>>
            ) {
                if (response.body() != null) {
                    populateItems(response.body()!!)
                }
            }

            override fun onFailure(call: Call<List<Item>>, t: Throwable) {
                Log.d(javaClass.name, t.message, t)
            }

        })
    }

    private fun populateItems(bookItems: List<Item>) {
        //GlobalScope.launch {

        bookItems.forEach {

            var values = ContentValues().apply {
                put(BookItem::title.name, it.title)
                put(BookItem::author.name, it.author)
                put(BookItem::rating.name, it.rating)
                put(BookItem::pages.name, it.pages)
                put(BookItem::published.name, it.published)
                put(BookItem::description.name, it.description)
                put(BookItem::cover.name, it.cover)
                put(BookItem::download.name, it.download)
            }
            context.contentResolver.insert(PROVIDER_CONTENT_URI, values)
        }
        context.sendBroadcast<Receiver>()
        //}
    }
}