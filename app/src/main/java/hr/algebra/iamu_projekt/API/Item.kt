package hr.algebra.iamu_projekt.API

import com.google.gson.annotations.SerializedName

class Item (
    @SerializedName("title") val title : String,
    @SerializedName("author") val author : String,
    @SerializedName("rating") val rating : Int,
    @SerializedName("pages") val pages : Int,
    @SerializedName("published") val published : Int,
    @SerializedName("description") val description : String,
    @SerializedName("cover") val cover : String,
    @SerializedName("download") val download : String
)