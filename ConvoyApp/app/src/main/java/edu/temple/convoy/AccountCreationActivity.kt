package edu.temple.convoy

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

private const val REGISTER_STORAGE_KEY = "register_storage_key"
private const val ACCOUNT_CREATION_SHAREDPREF = "creation_sharedpref"

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

        val accountCreationTitle = findViewById<TextView>(R.id.accountCreationTextView)


        findViewById<Button>(R.id.createAccountFirstButton).setOnClickListener {
            //
            val requestBody = RequestRegistration("create", username, firstname, lastname, password)

            CoroutineScope(Dispatchers.IO).launch {
                try {
                    val responseRegister = apiService.registerUser("create", username, firstname, lastname, password)
                    if (responseRegister.isSuccessful) {
                        // Account created successfully


                        // store the response body in a variable
                        val regResponse = responseRegister.toString()
                        Log.d("Account Creation Response", regResponse)

                        // save the response
                        // save the response data
                        saveDataToSharedPreferences(this@AccountCreationActivity, REGISTER_STORAGE_KEY, regResponse)

                        // if the response is successful, start the main convoy, otherwise inform the user of the failure
                        if (regResponse.contains("OK")) {
                            // intent variable to switch
                            val mainConvoyIntent =
                                Intent(this@AccountCreationActivity, MainConvoy::class.java)
                            mainConvoyIntent.putExtra(ACCOUNT_CREATION_SHAREDPREF, REGISTER_STORAGE_KEY)
                            startActivity(mainConvoyIntent)
                            Log.d("POST request Success", "Response body is null")
                        } else {
                            accountCreationTitle.text = "Account couldn't be created."
                        }
                        // For example, you can navigate to another activity
                        //startActivity(Intent(this@AccountCreation, AnotherActivity::class.java))
                    } else {
                        // Handle error response
                        Log.e("Account Creation Error", "Response code:")
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