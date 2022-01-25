package com.example.roomrecyclerview

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.roomrecyclerview.db.DBHelper
import com.example.roomrecyclerview.recycler.KantinAdapter
import com.example.roomrecyclerview.recycler.KantinModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var dbHelper: DBHelper
    private lateinit var recyclerView: RecyclerView
    private var adapter: KantinAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        dbHelper = DBHelper(this)

        setupListener()

    }

    override fun onStart() {
        super.onStart()
        initRecycler()
    }

    private fun initRecycler(){
        recyclerView = rvDataKantin
        adapter = KantinAdapter()
        recyclerView.adapter = adapter

        val data = dbHelper.readData()

        adapter?.addItems(data)
    }

    private fun setupListener() {
        add_data.setOnClickListener {
            startActivity(
                Intent(this@MainActivity, AddDataAct::class.java)
            )
        }

    }

}