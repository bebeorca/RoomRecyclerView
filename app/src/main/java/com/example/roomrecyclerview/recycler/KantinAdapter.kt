package com.example.roomrecyclerview.recycler

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.roomrecyclerview.MainActivity
import com.example.roomrecyclerview.R
import com.example.roomrecyclerview.db.DBHelper
import kotlinx.android.synthetic.main.card_item.view.*

class KantinAdapter: RecyclerView.Adapter<KantinAdapter.ViewHolder>() {

    private var kantinList: ArrayList<KantinModel> = ArrayList()

    fun addItems(items: ArrayList<KantinModel>){
        this.kantinList = items
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        LayoutInflater.from(parent.context)
            .inflate(R.layout.card_item, parent, false)
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = kantinList[position]
        holder.bindView(data)

    }

    override fun getItemCount() = kantinList.size

    class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        val id = view.tv_id
        val nk = view.tv_nk
        val np = view.tv_np
        val tp = view.tv_tp

        fun bindView(model: KantinModel){
            id.text = "ID: ${model.id}"
            nk.text = "NANA KANTIN: ${model.nama_kantin}"
            np.text = "NAMA PEMILIK: ${model.nama_pemilik}"
            tp.text = "TELEPON: ${model.telepon}"
        }

    }

}