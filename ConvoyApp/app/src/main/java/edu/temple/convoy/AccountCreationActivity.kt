package edu.temple.convoy

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class AccountCreationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.account_creation)

        // firstname, lastname, username, password
        val usernameEdit = findViewById<EditText>(R.id.userEditText)
        val passwordEdit = findViewById<EditText>(R.id.passwordEdit)
        val firsnameEdit = findViewById<EditText>(R.id.firstNameEdit)
        val lastnameEdit = findViewById<EditText>(R.id.lastNameEdit)

        findViewById<Button>(R.id.createAccountFirstButton).setOnClickListener {

        }
    }





}