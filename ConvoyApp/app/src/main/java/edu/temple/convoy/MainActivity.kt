package edu.temple.convoy

import android.app.DownloadManager.Request
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // local variables to store the pass + user + session key entered by the user
        var username: String
        var password: String
        var sessionKey: String
        val url = "https://kamorris.com/lab/convoy/account.php"



        findViewById<Button>(R.id.accountAccessButton).setOnClickListener {
            username = findViewById<EditText>(R.id.userEditText).text.toString()
            password = findViewById<EditText>(R.id.passwordEditText).text.toString()

            // POST request for logging in

            // hashmap for the POST request
            val params = HashMap<String,String>()
            params["username"] = username
            params["password"] = password
            val requestQueue = Volley.newRequestQueue(this)

            // getSharedPreferences(file name) or getPreferences(MODE_PRIVATE) in main or service to save
            // might need file object as well

            //val stringRequest = StringRequest()

        }
    }
}