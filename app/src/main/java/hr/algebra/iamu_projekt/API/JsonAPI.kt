package hr.algebra.iamu_projekt.API

import retrofit2.Call
import retrofit2.http.GET

const val URL = "https://my-json-server.typicode.com/"
interface JsonAPI {
    @GET("frankopapic/users-api/books")
    fun fetchItems() : Call<List<Item>>
}