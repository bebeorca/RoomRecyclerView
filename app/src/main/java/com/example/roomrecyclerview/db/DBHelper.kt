package com.example.roomrecyclerview.db

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.provider.BaseColumns
import com.example.roomrecyclerview.recycler.KantinModel

class DBHelper(context: Context): SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object{
        val DATABASE_NAME = "kantin.db"
        val DATABASE_VERSION = 1
        val ID = "id"
        val NAMA_TABLE = "tbl_kantin"
        val NAMA_KANTIN = "nama_kantin"
        val NAMA_PEMILIK = "nama_pemilik"
        val TELEPON = "telepon"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val cTable = ("CREATE TABLE "+ NAMA_TABLE + "(" +
                ID+" INTEGER PRIMARY KEY AUTOINCREMENT," +
                NAMA_KANTIN+" TEXT," +
                NAMA_PEMILIK+" TEXT," +
                TELEPON+" TEXT"+")")

        db?.execSQL(cTable)
    }

    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {
        db!!.execSQL("DROP TABLE IF EXISTS $NAMA_TABLE")
        onCreate(db)
    }

    fun insertData(kntin: KantinModel): Long{
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(ID, kntin.id)
        contentValues.put(NAMA_KANTIN, kntin.nama_kantin)
        contentValues.put(NAMA_PEMILIK, kntin.nama_pemilik)
        contentValues.put(TELEPON, kntin.telepon)

        val succes = db.insert(NAMA_TABLE, null, contentValues)
        db.close()

        return succes

    }


    @SuppressLint("Range")
    fun readData(): ArrayList<KantinModel>{

        val list: ArrayList<KantinModel> = ArrayList()
        val sq = "SELECT * FROM $NAMA_TABLE"
        val db = this.writableDatabase

        val cursor: Cursor?

        try {
            cursor = db.rawQuery(sq, null)
        }catch (e: Exception){
            db.execSQL(sq)
            return ArrayList()
        }

        var id: Int
        var nk: String
        var np: String
        var tp: String

        if(cursor.moveToFirst()){
            do {
                id = cursor.getInt(cursor.getColumnIndex("id"))
                nk = cursor.getString(cursor.getColumnIndex("nama_kantin"))
                np = cursor.getString(cursor.getColumnIndex("nama_pemilik"))
                tp = cursor.getString(cursor.getColumnIndex("telepon"))

                val lst = KantinModel(id = id, nama_kantin = nk, nama_pemilik = np, telepon = tp)
                list.add(lst)
            }while (cursor.moveToNext())
        }

        return list

    }

}