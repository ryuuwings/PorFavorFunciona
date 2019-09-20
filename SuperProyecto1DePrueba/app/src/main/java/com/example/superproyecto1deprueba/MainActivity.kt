package com.example.superproyecto1deprueba

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*

var id = ""
var text1 = ""
var text2 = ""



class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }

        buttonSendRequest.setOnClickListener {
            requestToServer();
        }
        productCheck.setOnClickListener {
            var intent = Intent(this, Product::class.java)
            validateID(intent)

            }

        }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    private fun requestToServer(){


        val queue = Volley.newRequestQueue(this)



        val url = "http://192.168.103.46:420/list"

        val jsonObjectRequest = JsonObjectRequest(
            Request.Method.GET, url, null,
            Response.Listener { response ->
                textView2.text = "List of products: %s".format(response.toString())

            },
            Response.ErrorListener {
            }
        )
        queue.add(jsonObjectRequest);
    }
    private fun requestToServer2(id:String, intent: Intent){

        val queue = Volley.newRequestQueue(this)



        val url = "http://192.168.103.46:420/product/$id"

        val jsonObjectRequest = JsonObjectRequest(
            Request.Method.GET, url, null,
            Response.Listener { response ->
                text1= response.get("product").toString()
                text2 = response.get("price").toString()
                doIntent(intent)
            },
            Response.ErrorListener {
            }
        )
        queue.add(jsonObjectRequest);
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
    private fun doIntent(intent: Intent){
        intent.putExtra("product",text1)
        intent.putExtra("price",text2)
        startActivity(intent)
    }
    private fun validateID(intent: Intent){
        textInput.setError(null)
        id = textInput.getText().toString()
        if(TextUtils.isEmpty(textInput.text)){
            textInput.setError(getString(R.string.error_blank_input))
            textInput.requestFocus()
            return
        }
        var numberID:Int = Integer.parseInt(textInput.text.toString())
        if(numberID<1 || numberID>9){
            textInput.setError(getString(R.string.error_invalid_number))
            textInput.requestFocus()
            return
        }
        requestToServer2(id, intent)

    }
}
