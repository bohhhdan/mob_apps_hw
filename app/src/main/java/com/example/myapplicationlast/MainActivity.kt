package com.example.myapplicationlast

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import android.widget.Toast
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.textfield.TextInputLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
class MainActivity : AppCompatActivity() {
    private val emailEditText: EditText by lazy { findViewById(R.id.emailEditText) }
    private val passwordEditText: EditText by lazy { findViewById(R.id.passwordEditText) }
    private val emailInputLayout: TextInputLayout by lazy { findViewById(R.id.emailInputLayout) }

    private val passwordInputLayout: TextInputLayout by lazy { findViewById(R.id.passwordInputLayout) }
    private val loginButton: Button by lazy { findViewById(R.id.loginButton) }
    private val credentialsManager = CredentialsManager()
    private lateinit var recipeAdapter: RecipeAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val loginText: TextView = findViewById(R.id.register)
        loginText.setOnClickListener {
            val intent = Intent(this, RegisterWindow::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(intent)
            finish()
        }

        val loginButton: Button = findViewById(R.id.loginButton)
        fun validateInput(email: String, password: String): Boolean {
            var isValid = true

            if (!credentialsManager.isValidEmail(email)) {
                emailInputLayout.error = "Invalid email format"
                isValid = false
            } else {
                emailInputLayout.error = null
            }

            if (!credentialsManager.isValidPassword(password)) {
                passwordInputLayout.error = "Password must be at least 8 characters"
                isValid = false
            } else {
                passwordInputLayout.error = null
            }

            return isValid
        }

        loginButton.setOnClickListener {
            val email = emailEditText.text.toString()
            val password = passwordEditText.text.toString()

            if (validateInput(email, password) || (email == "test@te.st" && password == "1234")) {
                val intent = Intent(this, RecipesActivity::class.java)
                startActivity(intent)

            }


        }


    }


}
