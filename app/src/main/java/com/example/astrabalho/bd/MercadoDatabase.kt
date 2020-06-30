package com.example.astrabalho.bd

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import org.jetbrains.anko.db.*
import java.time.Instant
import java.util.*

class MercadoDatabase(context: Context) : ManagedSQLiteOpenHelper(ctx = context, name="mercado.db", version = 11) {

    companion object {
        private var instance: MercadoDatabase? = null

        @Synchronized
        fun getInstance(ctx: Context) : MercadoDatabase {
            if (instance == null) {
                instance = MercadoDatabase(ctx.applicationContext)
            }
            return instance!!
        }
    }
    override fun onCreate(db: SQLiteDatabase) {
        db.createTable("Ativos", true,
            "id" to INTEGER + PRIMARY_KEY  + UNIQUE,
            "apelido" to TEXT,
            "quantidade" to REAL,
            "valor" to REAL)
    }
    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.dropTable("Ativos", true)
    }
}

val Context.database : MercadoDatabase
get() = MercadoDatabase.getInstance(applicationContext)

