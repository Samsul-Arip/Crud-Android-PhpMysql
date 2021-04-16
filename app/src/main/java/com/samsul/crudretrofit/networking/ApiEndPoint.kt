package com.samsul.crudretrofit.networking

import com.samsul.crudretrofit.model.ResponseModel
import retrofit2.Call
import retrofit2.http.*

interface ApiEndPoint {

    @FormUrlEncoded
    @POST("tambahPgw.php")
    fun insertPegawai(
        @Field("nama") nama : String,
        @Field("alamat") alamat : String,
        @Field("jenis_kelamin") jk : String,
    ) : Call<String>

    @GET("tampilSemuaPgw.php")
    fun getData() : Call<ResponseModel>

    @GET("hapusPgw.php")
    fun deleteData(
            @Query("id") id : String
    ) : Call<String>

    @FormUrlEncoded
    @POST("updatePgw.php")
    fun updataPegawai(
        @Field("id") id : String,
        @Field("nama") nama : String,
        @Field("alamat") alamat : String,
        @Field("jenis_kelamin") jk : String,
    ) : Call<String>
}