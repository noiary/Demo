package com.maodq.demo.internal

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebChromeClient
import android.webkit.WebViewClient
import com.maodq.demo.R
import kotlinx.android.synthetic.main.activity_web_view.*

private const val url = "file:///android_asset/web/web.html"

class WebViewActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web_view)

        initWebView()
        web_view.loadUrl(url)

    }

    private fun initWebView() {
        web_view?.apply {
            settings.javaScriptEnabled = true
            settings.domStorageEnabled = true
            webViewClient = WebViewClient()
            webChromeClient = object : WebChromeClient() {

            }
        }
    }
}
