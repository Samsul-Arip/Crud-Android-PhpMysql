package com.samsul.crudretrofit.model

import com.google.gson.annotations.SerializedName

data class DataModel(
    @SerializedName("id") val id : String,
    @SerializedName("name") val nama : String,
    @SerializedName("alamat") val alamat : String,
    @SerializedName("jenis_kelamin") val jk : String,
)
