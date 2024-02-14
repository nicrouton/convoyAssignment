package edu.temple.convoy

import android.app.DownloadManager.Request
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.POST


private const val loginAction = "LOGIN"
private const val registerAction = "REGISTER"
private const val logoutAction = "LOGOUT"

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // local variables to store the pass + user + session key entered by the user
        var username: String
        var password: String
        var sessionKey: String
        val URL ="https://kamorris.com/lab/convoy/account.php/"

        val loginTextView = findViewById<TextView>(R.id.loginAccountTextView)


        // Retrofit instance to create an instance of your API service
        val apiService = RetrofitInstance.retrofit.create(MyApiService::class.java)






        // if the log in button is pressed, program makes a POST request
        findViewById<Button>(R.id.accountAccessButton).setOnClickListener {
            username = findViewById<EditText>(R.id.userEditText).text.toString()
            password = findViewById<EditText>(R.id.passwordEditText).text.toString()

            // coroutine scope
            val scope = CoroutineScope(Job() + Dispatchers.Main)

            // POST request for logging in

            // hashmap for the POST request
            val params = HashMap<String,String>()
            params["username"] = username
            params["password"] = password

            val requestBody = RequestUserLogin(loginAction, username, password) // create an instance of your request body
            scope.launch {
                Log.d("Coroutine launch", "Code in Coroutine scope(main) running")
                //val call = apiService.registerUser(loginAction, username, password)
                //  Make sure to replace "endpoint" with your actual API endpoint and define MyResponse accordingly.
                /*call.enqueue(object : Callback<ResponseData> {
                    override fun onResponse(call: Call<ResponseData>, response: Response<ResponseData>) {
                        if (response.isSuccessful) {
                            Log.d("POST request Success", "onResponse success")
                            val myResponse = response.body()
                            // Handle successful response
                            loginTextView.text = myResponse.toString()

                        } else {
                            // Handle error response
                            Log.d("POST request ERROR", "onResponse error")
                        }
                    }

                    override fun onFailure(call: Call<ResponseData>, t: Throwable) {
                        // Handle network error
                        Log.d("Network Failure onResponse", "Network error")
                    }
                })
            }*/
                try {
                    val response = apiService.loginUser(loginAction, username, password)
                    if (response.isSuccessful) {
                        val responseData = response.body()
                        if (responseData != null) {
                            Log.d("POST request Success", "onResponse success")
                            loginTextView.text = responseData.toString()
                        } else {
                            Log.d("POST request Success", "Response body is null")
                        }
                    } else {
                        Log.d("POST request ERROR", "Response code: ${response.code()}")
                    }
                } catch (e: Exception) {
                    Log.e("Network Failure onResponse", "Error: ${e.message}", e)
                }
            }






            // getSharedPreferences(file name) or getPreferences(MODE_PRIVATE) in main or service to save
            // might need file object as well

            //val stringRequest = StringRequest()

        }

        // add account createAccountButtom functionality
        findViewById<Button>(R.id.createAccountButton).setOnClickListener {
            // launch new activity for account creation when button pressed
            Log.d("Account Creation","Launch account creation")
            val creationIntent = Intent(this@MainActivity, AccountCreationActivity::class.java)
            startActivity(creationIntent)
        }
    }

}

object RetrofitInstance {

    private const val BASE_URL ="https://kamorris.com/lab/convoy/" // replace with your base URL

    val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}