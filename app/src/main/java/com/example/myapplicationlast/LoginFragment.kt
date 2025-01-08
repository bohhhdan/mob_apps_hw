package com.example.myapplicationlast

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.android.material.textfield.TextInputLayout

class LoginFragment : Fragment() {

    private lateinit var emailEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var emailInputLayout: TextInputLayout
    private lateinit var passwordInputLayout: TextInputLayout
    private lateinit var loginButton: Button
    private lateinit var registerText: TextView

    private val credentialsManager = CredentialsManager.instance

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_login, container, false)

        // Initialize UI elements
        emailEditText = view.findViewById(R.id.emailEditText)
        passwordEditText = view.findViewById(R.id.passwordEditText)
        emailInputLayout = view.findViewById(R.id.emailInputLayout)
        passwordInputLayout = view.findViewById(R.id.passwordInputLayout)
        loginButton = view.findViewById(R.id.loginButton)
        registerText = view.findViewById(R.id.register)

        // Set listeners
        loginButton.setOnClickListener {
            completeLogin()
        }
        registerText.setOnClickListener {
            gotoRegister()
        }

        return view
    }

    private fun completeLogin() {
        val email = emailEditText.text.toString()
        val password = passwordEditText.text.toString()

        if (validateInput(email, password) || email == "test@te.st" && password == "1234") {
            Toast.makeText(requireContext(), "Login success!", Toast.LENGTH_LONG).show()
        }
    }

    private fun gotoRegister() {
        val fragment = RegisterFragment()
        parentFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, fragment)
            .addToBackStack(null)  // Allow back navigation to this fragment
            .commit()
    }

    private fun validateInput(email: String, password: String): Boolean {
        var isValid = true

        if (!credentialsManager.isValidEmail(email)) {
            emailInputLayout.error = "Invalid email format"
            isValid = false
        } else {
            emailInputLayout.error = null
        }

        if (!credentialsManager.isValidPassword(password)) {
            passwordInputLayout.error = "Invalid password"
        }
        else{
            passwordInputLayout.error = null
        }
        return isValid
    }

}
