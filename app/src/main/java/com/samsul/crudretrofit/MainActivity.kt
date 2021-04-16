package com.samsul.crudretrofit

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_inputData.setOnClickListener {
            startActivity(Intent(this@MainActivity, InsertActivity::class.java))
        }
        btn_tampilData.setOnClickListener {
            startActivity(Intent(this, TampilActivity::class.java))
        }
    }
}