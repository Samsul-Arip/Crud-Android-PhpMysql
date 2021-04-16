package com.samsul.crudretrofit

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.samsul.crudretrofit.networking.ApiService
import kotlinx.android.synthetic.main.activity_insert.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class InsertActivity : AppCompatActivity() {

    var jk : String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_insert)

        val nama = findViewById<TextView>(R.id.edt_nama)
        val alamat = findViewById<TextView>(R.id.edt_alamat)
        btn_simpan.setOnClickListener {
            val selected : Int = radioGroup.checkedRadioButtonId
            if(selected == rb_pria.id){
                jk = "Laki - Laki"
            } else {
                jk = "Perempuan"
            }
            ApiService.endPoint.insertPegawai(nama.text.toString(), alamat.text.toString(), jk)
                    .enqueue(object : Callback<String>{
                        override fun onResponse(call: Call<String>, response: Response<String>) {
                        }
                        override fun onFailure(call: Call<String>, t: Throwable) {
                        }
                    })
            startActivity(Intent(applicationContext, MainActivity::class.java))
            this.finish()
        }

    }

}