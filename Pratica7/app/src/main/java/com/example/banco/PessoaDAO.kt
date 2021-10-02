package com.example.banco

import android.content.ContentValues
import android.content.Context
import android.util.Log

class PessoaDAO (context: Context) {
    private lateinit var banco: BancoHelper

    init {
        this.banco = BancoHelper(context)
    }

    fun add(pessoa: Pessoa){
        val cv = ContentValues()
        cv.put("nome", pessoa.nome)
        cv.put("datahora", pessoa.datahora)
        this.banco.writableDatabase.insert("pessoas", null, cv)
    }

    fun get(): MutableList<Pessoa>{
        val lista = mutableListOf<Pessoa>()
        val colunas = arrayOf("id", "nome", "datahora")
        val cursor = this.banco.readableDatabase.query("pessoas", colunas, null, null, null, null, null)
        cursor.moveToFirst()
        for (i in 1..cursor.count){
            val id = cursor.getInt(cursor.getColumnIndex("id"))
            val nome = cursor.getString(cursor.getColumnIndex("nome"))
            val datahora = cursor.getLong(cursor.getColumnIndex("datahora"))
            lista.add(Pessoa(id, nome, datahora))
            cursor.moveToNext()
        }

        return lista
    }

    fun get(id: Int): Pessoa?{
        var pessoa: Pessoa? = null
        val where = "id = ?"
        val pwhere = arrayOf(id.toString())
        val colunas = arrayOf("id", "nome", "datahora")
        val cursor = this.banco.readableDatabase.query("pessoas", colunas, where, pwhere, null, null, null)
        if (cursor.moveToFirst()){
            Log.i("DAO", "Entrou")
            val id = cursor.getInt(cursor.getColumnIndex("id"))
            val nome = cursor.getString(cursor.getColumnIndex("nome"))
            val datahora = cursor.getLong(cursor.getColumnIndex("datahora"))
            pessoa = Pessoa(id, nome, datahora)
            Log.i("DAO", pessoa.toString())
        }


        return pessoa
    }

    fun delete(pessoa: Pessoa){
        val where = "id = ?"
        val pWhere = arrayOf(pessoa.id.toString())
        this.banco.writableDatabase.delete("pessoas", where, pWhere)
    }

    fun update(pessoa: Pessoa){
        val cv = ContentValues()
        val where = "id = ?"
        val pWhere = arrayOf(pessoa.id.toString())

        cv.put("nome", pessoa.nome)
        this.banco.writableDatabase.update("pessoas", cv, where, pWhere)
    }
}