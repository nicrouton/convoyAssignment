package edu.temple.convoy

import android.app.DownloadManager.Request
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.POST

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // local variables to store the pass + user + session key entered by the user
        var username: String
        var password: String
        var sessionKey: String


        // Retrofit instance to create an instance of your API service
        val apiService = RetrofitInstance.retrofit.create(MyApiService::class.java)







        findViewById<Button>(R.id.accountAccessButton).setOnClickListener {
            username = findViewById<EditText>(R.id.userEditText).text.toString()
            password = findViewById<EditText>(R.id.passwordEditText).text.toString()

            // POST request for logging in

            // hashmap for the POST request
            val params = HashMap<String,String>()
            params["username"] = username
            params["password"] = password

            val requestBody = RequestRegistration("value1", "value2", "b", "sd", "") // create an instance of your request body
            val call = apiService.postData(requestBody)

            //  Make sure to replace "endpoint" with your actual API endpoint and define MyResponse accordingly.
            call.enqueue(object : Callback<ResponseData> {
                override fun onResponse(call: Call<ResponseData>, response: Response<ResponseData>) {
                    if (response.isSuccessful) {
                        val myResponse = response.body()
                        // Handle successful response
                    } else {
                        // Handle error response
                    }
                }

                override fun onFailure(call: Call<ResponseData>, t: Throwable) {
                    // Handle network error
                }
            })



            // getSharedPreferences(file name) or getPreferences(MODE_PRIVATE) in main or service to save
            // might need file object as well

            //val stringRequest = StringRequest()

        }
    }
}

object RetrofitInstance {

    private const val BASE_URL ="https://kamorris.com/lab/convoy/account.php" // replace with your base URL

    val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}