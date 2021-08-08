package com.example.pratica1

import android.os.Bundle
import android.Manifest
import android.Manifest.permission.CAMERA
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.Camera
import android.provider.MediaStore
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat



class MainActivity : AppCompatActivity() {
    //Eu pensei em utilizar a flag pra verificar se a pessoa tinha
    // ou não aceitado após a requisição novamente, mas sendo bem sincero,
    //não soube pensar no metodo de fazeru o override do onrequestpermissionresult
    //e pelo tempo que tivemos e outros motivos de tempo, achei melhor deixar isso para depois.
    //Peço que perdoe essa parte.

//    private var flag: Boolean = false

    private lateinit var btnFoto: Button
    private lateinit var imgFoto: ImageView
    private val Codigo_Camera = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        verificaPermissao()

        this.btnFoto = findViewById(R.id.btnFoto)
        this.imgFoto = findViewById(R.id.imagemFoto)
        this.btnFoto.setOnClickListener {
            val chamadaCamera = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            startActivityForResult(chamadaCamera, Codigo_Camera);
        }
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        //Verifica sobre dados e requisição e pá, e faz a magica
        if (data != null && requestCode == Codigo_Camera && resultCode == Activity.RESULT_OK) {
            val fotoBitMap = data?.getParcelableExtra<Bitmap>("data")
            this.imgFoto.setImageBitmap(fotoBitMap)
        }
    }

    //Sofrimen- Digo, permissões
    private fun verificaPermissao(){
        //Se não tiver sido permitido, pede pra permitir.
        if(!getPermissaoCamera()) setPermissaoCamera()
        //else flag = true
    }

    //Retorna se tem permissão para usar a camera
    private fun getPermissaoCamera(): Boolean {
        return (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED)
    }

    private fun setPermissaoCamera() {
        //Pega a lista de permissões necessarias pra usar a camera e pede liberação
        val listaPermissoes = listOf<String>(
            Manifest.permission.CAMERA
        )
        //pede pra essa atividade, a lista (com seus respectivos tipos), que estejam true (1)
        ActivityCompat.requestPermissions(this, listaPermissoes.toTypedArray(), 1)
    }
}