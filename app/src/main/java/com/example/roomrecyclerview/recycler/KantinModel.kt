package com.example.roomrecyclerview.recycler

import java.util.*

data class KantinModel(
    var id: Int = generateId(),
    var nama_kantin: String = "",
    var nama_pemilik: String = "",
    var telepon: String = "",
){
    companion object{
        fun generateId(): Int{
            val ran = Random()
            return ran.nextInt(100)
        }
    }
}

