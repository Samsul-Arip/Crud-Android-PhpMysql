package com.samsul.crudretrofit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.samsul.crudretrofit.adapter.DataAdapter
import com.samsul.crudretrofit.model.DataModel
import com.samsul.crudretrofit.model.ResponseModel
import com.samsul.crudretrofit.networking.ApiService
import kotlinx.android.synthetic.main.activity_tampil.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TampilActivity : AppCompatActivity() {

    lateinit var dataAdapter: DataAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tampil)

        dataAdapter = DataAdapter(this, arrayListOf())
        recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = dataAdapter
        }
        getData()
        swipe.setOnRefreshListener {
            getData()
        }

    }
    fun getData(){
        onLoading(true)
        ApiService.endPoint.getData().enqueue(object : Callback<ResponseModel>{
            override fun onResponse(call: Call<ResponseModel>, response: Response<ResponseModel>) {
                onLoading(false)
                if(response.isSuccessful){
                    showResult(response.body()!!)
                }

            }

            override fun onFailure(call: Call<ResponseModel>, t: Throwable) {
                onLoading(false)
                Log.d("TampilActivity", t.message.toString())
            }

        })
    }

    fun showResult(results : ResponseModel){
        dataAdapter.setData(results.result)
    }
    fun onLoading(loading : Boolean){
        when(loading){
            true -> swipe.isRefreshing = true
            false -> swipe.isRefreshing = false
        }
    }
}