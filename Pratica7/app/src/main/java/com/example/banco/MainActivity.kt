package com.example.banco

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AlertDialog

class MainActivity : AppCompatActivity() {
    private lateinit var bt: Button
    private lateinit var dao: PessoaDAO

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        this.dao = PessoaDAO(this)

        this.bt = findViewById(R.id.button)

        this.bt.setOnClickListener{
            var edit = EditText(this)
            val popup = AlertDialog.Builder(this).apply {
                setTitle("Nova Pessoa")
                setMessage("Informe o nome: ")
                setView(edit)
                setPositiveButton("Salvar", DialogInterface.OnClickListener{ dialog, which ->
                    val pessoa = Pessoa(edit.text.toString())
                    this@MainActivity.dao.add(pessoa)
                    Log.i("APP_BANCO", this@MainActivity.dao.get().toString())
                })
                setNegativeButton("Cancelar", null)
            }
            popup.create().show()
        }

        this.bt.setOnLongClickListener{
            val pessoas = this.dao.get()
            val pessoa = pessoas.get(0)
            pessoa.nome = "outro nome"
            Log.i("APP_BANCO", pessoas.toString())

            val pessoaEspecifica = this.dao.get(4)
            Log.i("APP_BANCO", pessoaEspecifica.toString())

//            this.dao.update(pessoa)
//            Log.i("APP_BANCO", this@MainActivity.dao.get().toString())

//            this.dao.delete(pessoa)
//            Log.i("APP_BANCO", this@MainActivity.dao.get().toString())
            return@setOnLongClickListener true
        }
    }
}