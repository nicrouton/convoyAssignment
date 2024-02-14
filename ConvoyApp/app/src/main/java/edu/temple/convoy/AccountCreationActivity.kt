package edu.temple.convoy

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AccountCreationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.account_creation)

        // firstname, lastname, username, password
        val usernameEdit = findViewById<EditText>(R.id.usernameEdit)
        val passwordEdit = findViewById<EditText>(R.id.passwordEdit)
        val firsnameEdit = findViewById<EditText>(R.id.firstNameEdit)
        val lastnameEdit = findViewById<EditText>(R.id.lastNameEdit)

        var username = usernameEdit.text.toString()
        var password = passwordEdit.text.toString()
        var firstname = firsnameEdit.text.toString()
        var lastname = lastnameEdit.text.toString()

        val apiService = RetrofitInstance.retrofit.create(MyApiService::class.java)


        findViewById<Button>(R.id.createAccountFirstButton).setOnClickListener {
            //
            val requestBody = RequestRegistration("create", username, firstname, lastname, password)

            CoroutineScope(Dispatchers.IO).launch {
                try {
                    val response = apiService.registerUser("create", username, firstname, lastname, password)
                    if (response.isSuccessful) {
                        // Account created successfully, handle accordingly
                        findViewById<TextView>(R.id.accountCreationTextView).text = response.toString()

                        // For example, you can navigate to another activity
                        //startActivity(Intent(this@AccountCreation, AnotherActivity::class.java))
                    } else {
                        // Handle error response
                        Log.e("Account Creation Error", "Response code: ${response.code()}")
                        // You can show a message to the user indicating the failure
                    }
                } catch (e: Exception) {
                    // Handle network errors
                    Log.e("Network Error", "Error: ${e.message}", e)
                }
            }
        }
    }





}