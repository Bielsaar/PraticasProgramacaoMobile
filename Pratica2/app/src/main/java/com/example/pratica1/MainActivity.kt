package com.example.pratica1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    private lateinit var txtVwInfoSobre: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        this.txtVwInfoSobre = findViewById(R.id.txtVwInfoSobre);
    }

    fun MostrarInfo(view: View){
        this.txtVwInfoSobre.text = "Resgata o numero de série do dispositivo"
    }
}