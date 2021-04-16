package com.samsul.crudretrofit.adapter

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.samsul.crudretrofit.MainActivity
import com.samsul.crudretrofit.R
import com.samsul.crudretrofit.UpdateActivity
import com.samsul.crudretrofit.model.DataModel
import com.samsul.crudretrofit.networking.ApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DataAdapter(val context : Context, val list: ArrayList<DataModel>) :
    RecyclerView.Adapter<DataAdapter.DataViewHolder>() {

    inner class DataViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        val id : TextView = itemView.findViewById(R.id.tv_id)
        val nama : TextView = itemView.findViewById(R.id.tv_nama)
        val alamat : TextView = itemView.findViewById(R.id.tv_alamat)
        val jenisKelamin : TextView = itemView.findViewById(R.id.tv_jk)

        fun bind(model: DataModel){
            id.text = model.id
            nama.text = model.nama
            alamat.text = model.alamat
            jenisKelamin.text = model.jk
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.list_data, parent, false)
        return DataViewHolder(view)
    }

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        holder.bind(list[position])
        holder.itemView.setOnClickListener {
            val options = arrayOf<CharSequence>(
                    "Edit Data",
                    "Hapus Data"
            )
            val builder : AlertDialog.Builder = AlertDialog.Builder(context)
            builder.setTitle("What do you want?")
            builder.setItems(options, DialogInterface.OnClickListener { dialog, position ->
                if(position == 0) {
                    val intent = Intent(context, UpdateActivity::class.java)
                    intent.putExtra("id", holder.id.text.toString()) 
                    intent.putExtra("nama", holder.nama.text.toString())
                    intent.putExtra("alamat", holder.alamat.text.toString())
                    intent.putExtra("jk", holder.jenisKelamin.text.toString())
                    context.startActivity(intent)
                }
                if(position == 1) {
                    ApiService.endPoint.deleteData(holder.id.text.toString()).enqueue(object : Callback<String>{
                        override fun onResponse(call: Call<String>, response: Response<String>) {
                            if(response.isSuccessful){
                                Toast.makeText(context, "Dihapus", Toast.LENGTH_LONG).show()
                            }
                        }

                        override fun onFailure(call: Call<String>, t: Throwable) {

                        }

                    })

                }
            })
            builder.show()
        }
    }

    override fun getItemCount(): Int = list.size

    fun setData(data : List<DataModel>){
        list.clear()
        list.addAll(data)
        notifyDataSetChanged()
    }

}