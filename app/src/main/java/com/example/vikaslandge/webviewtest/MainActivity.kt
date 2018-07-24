package com.example.vikaslandge.webviewtest

import android.app.ProgressDialog
import android.graphics.Bitmap
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.webkit.JavascriptInterface
import android.webkit.WebResourceRequest
import android.widget.Toast
import android.webkit.WebView
import android.webkit.WebViewClient
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var pDialog : ProgressDialog ? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        pDialog = ProgressDialog(this)
        pDialog!!.setMessage("Please wait page is loading")
        wview.webViewClient = object : WebViewClient(){

            override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                super.onPageStarted(view, url, favicon)
                pDialog!!.show()
            }

            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
                pDialog!!.dismiss()
            }
            //override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {
              //  view?.loadUrl(request?.url.toString())
                //return true
        }
        wview.settings.javaScriptEnabled =true
        wview.settings.builtInZoomControls =true
        wview.addJavascriptInterface(this,"and7amjuly")
    }
    fun load(v:View){

        when(v.id){

            R.id.b1 -> if (et1.text.toString().contains("http://")){
                wview.loadUrl(et1.text.toString())
            }else{
                wview.loadUrl("http://"+et1.text.toString())
            }

            R.id.b2 -> wview.loadUrl("http://www.facebook.com")
            R.id.b3 -> wview.loadUrl("http://www.google.com")
            R.id.b4 -> wview.loadUrl("http://www.twitter.com")
            R.id.b5 -> wview.loadUrl("http://www.linkedin.com")
            R.id.html -> wview.loadUrl("file:///android_asset/sample.html");

        }
    }
    @JavascriptInterface
    fun displayMsg(email:String,pass:String)
    {
        Toast.makeText(this,
                email+"\n"+pass,Toast.LENGTH_LONG).show()
    }
}
