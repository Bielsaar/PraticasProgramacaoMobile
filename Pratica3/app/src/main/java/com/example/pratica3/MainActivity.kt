package com.example.pratica3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Button

class MainActivity : AppCompatActivity() {
    private lateinit var webView: WebView
    private lateinit var webBotao: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        this.webBotao = findViewById(R.id.botao)
        this.webView = findViewById(R.id.webview)
        this.webView.webViewClient = WebViewClient()
        this.webView.settings.javaScriptEnabled = true

    }

    fun OnClickBotao(view: View) {
        loadWebView()
    }

    private fun loadWebView() {
        this.webView.loadUrl("https://www.youtube.com/watch?v=dQw4w9WgXcQ")
    }


}