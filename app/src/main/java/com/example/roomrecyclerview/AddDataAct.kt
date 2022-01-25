package com.example.roomrecyclerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.roomrecyclerview.db.DBHelper
import com.example.roomrecyclerview.recycler.KantinModel
import kotlinx.android.synthetic.main.activity_add_data2.*

class AddDataAct : AppCompatActivity() {

    private lateinit var dbHelper: DBHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_data2)

        dbHelper = DBHelper(this)

        save_data_btn.setOnClickListener {
            addKantin()
        }

    }

    private fun addKantin() {
        val nama_kantin = nama_kantin.text.toString()
        val nama_pemilik = nama_pemilik.text.toString()
        val telepon = telepon.text.toString()

        if(nama_kantin.isEmpty() || nama_kantin.isEmpty() || telepon.isEmpty()){
            Toast.makeText(this, "please enter required field", Toast.LENGTH_SHORT).show()
        }else{
            val kantin = KantinModel(nama_kantin = nama_kantin, nama_pemilik = nama_pemilik, telepon = telepon)
            val status = dbHelper.insertData(kantin)

            if(status > -1){
                Toast.makeText(this, "Data kantin tersimpan", Toast.LENGTH_SHORT).show()
                clearTextField()
            }else Toast.makeText(this, "Data kantin tidak tersimpan", Toast.LENGTH_SHORT).show()

        }

    }

    fun clearTextField(){
        nama_kantin.text.clear()
        nama_pemilik.text.clear()
        telepon.text.clear()
    }


}