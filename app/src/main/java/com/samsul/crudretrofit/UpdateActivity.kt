package com.samsul.crudretrofit

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.samsul.crudretrofit.networking.ApiService
import kotlinx.android.synthetic.main.activity_update.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UpdateActivity : AppCompatActivity() {

    var jenisKelamin : String = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update)

        val intent = intent
        val id = intent.getStringExtra("id")
        val nama = intent.getStringExtra("nama")
        val alamat = intent.getStringExtra("alamat")


        edt_id.setText(id)
        edt_nama.setText(nama)
        edt_alamat.setText(alamat)


        btn_edit.setOnClickListener {
            val pilihan : Int = radioGroup.checkedRadioButtonId
            if(pilihan == rb_pria.id){
                jenisKelamin = "Laki - Laki"
            } else {
                jenisKelamin = "Perempuan"
            }
            ApiService.endPoint.updataPegawai(edt_id.text.toString(),edt_nama.text.toString(), edt_alamat.text.toString(), jenisKelamin)
                .enqueue(object : Callback<String>{
                    override fun onResponse(call: Call<String>, response: Response<String>) {

                    }

                    override fun onFailure(call: Call<String>, t: Throwable) {

                    }

                })
            startActivity(Intent(this, TampilActivity::class.java))
            this.finish()
        }
    }
}