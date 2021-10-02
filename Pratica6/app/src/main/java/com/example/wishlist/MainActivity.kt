package com.example.wishlist

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {
    private lateinit var rvDesejos: RecyclerView
    private lateinit var fabAdd: FloatingActionButton
    private lateinit var lista: MutableList<Desejo>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        this.lista = mutableListOf()

        this.rvDesejos = findViewById(R.id.rvDesejos)
        this.fabAdd = findViewById(R.id.fabAdd)

        this.rvDesejos.adapter = DesejoAdapter(this.lista)
        (this.rvDesejos.adapter as DesejoAdapter).listener = OnItemClickListner()
        (this.rvDesejos.adapter as DesejoAdapter).listenerLong = OnItemLongClickListner()


        val resultForm = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
            if (it.resultCode == RESULT_OK){
                val desejo = it.data?.getSerializableExtra("DESEJO") as Desejo
                this.lista.add(desejo)
                (this.rvDesejos.adapter as DesejoAdapter).notifyDataSetChanged()
            }
        }

        this.fabAdd.setOnClickListener{
            val intent = Intent(this, FormActivity::class.java)
            resultForm.launch(intent)
        }


    }




    inner class OnItemClick: AdapterView.OnItemClickListener{
        override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
            val desejo = this@MainActivity.lista[position]
            Toast.makeText(this@MainActivity, desejo.descricao, Toast.LENGTH_SHORT).show()
        }
    }

    inner class OnItemLongClick: AdapterView.OnItemLongClickListener{
        override fun onItemLongClick(
            parent: AdapterView<*>?,
            view: View?,
            position: Int,
            id: Long
        ): Boolean {
            //Sofri tentando fazer uma classe pra depois perceber
            //que o proprio site diz que eu posso usar como eu quero usando como tá ai, jesus
            val itemRemover = this@MainActivity.lista.get(position)
            val alerta = AlertDialog.Builder(this@MainActivity)
            alerta.setTitle("-Deletar item-")
            alerta.setMessage("Você irá remover ´${itemRemover.descricao}´." +
                    "\n Você confirma?")
            alerta.setPositiveButton("Sim", DialogInterface.OnClickListener{
                dialogo: DialogInterface, x: Int ->
                    this@MainActivity.lista.removeAt(position)
                    (this@MainActivity.rvDesejos.adapter as DesejoAdapter).notifyDataSetChanged()
            })
            alerta.setNegativeButton("Não", null)
            alerta.create().show()
            return true
        }
    }

    inner class OnItemClickListner : OnItemClickRecycleView {
        override fun OnItemClick(position: Int) {
            val desejo = this@MainActivity.lista[position]
            Toast.makeText(this@MainActivity, desejo.descricao, Toast.LENGTH_SHORT).show()

        }

    }

    inner class OnItemLongClickListner: OnItemLongClickRecycleView {
        override fun OnItemLongClick(position: Int): Boolean {
            val itemRemover = this@MainActivity.lista.get(position)
            val alerta = AlertDialog.Builder(this@MainActivity)
            alerta.setTitle("-Deletar item-")
            alerta.setMessage("Você irá remover ´${itemRemover.descricao}´." +
                    "\n Você confirma?")
            alerta.setPositiveButton("Sim", DialogInterface.OnClickListener{
                    dialogo: DialogInterface, x: Int ->
                this@MainActivity.lista.removeAt(position)
                (this@MainActivity.rvDesejos.adapter as DesejoAdapter).notifyDataSetChanged()
            })
            alerta.setNegativeButton("Não", null)
            alerta.create().show()
            return true
        }

    }

}