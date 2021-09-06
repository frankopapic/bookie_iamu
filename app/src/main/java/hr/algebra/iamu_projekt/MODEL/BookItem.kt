package hr.algebra.iamu_projekt.MODEL

import com.google.gson.annotations.SerializedName

class BookItem (
    var _id : Long?,
    val title : String,
    val author : String,
    val rating : Int,
    val pages : Int,
    val published : Int,
    val description : String,
    val cover : String,
    val download : String
)