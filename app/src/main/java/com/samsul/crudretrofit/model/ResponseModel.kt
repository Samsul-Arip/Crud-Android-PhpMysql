package com.samsul.crudretrofit.model

import com.google.gson.annotations.SerializedName

data class ResponseModel(
    @SerializedName("result") val result : List<DataModel>
)
