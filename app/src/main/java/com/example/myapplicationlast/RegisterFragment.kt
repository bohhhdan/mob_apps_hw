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

class RegisterFragment : Fragment() {

    private lateinit var emailEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var emailInputLayout: TextInputLayout
    private lateinit var passwordInputLayout: TextInputLayout
    private lateinit var registerButton: Button
    private lateinit var loginText: TextView

    private val credentialsManager = CredentialsManager.instance

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_register, container, false)

        emailEditText = view.findViewById(R.id.emailEditText)
        passwordEditText = view.findViewById(R.id.passwordEditText2)
        emailInputLayout = view.findViewById(R.id.emailInputLayout)
        passwordInputLayout = view.findViewById(R.id.passwordInputLayout)
        registerButton = view.findViewById(R.id.loginButton)
        loginText = view.findViewById(R.id.register)

        registerButton.setOnClickListener {
            completeRegistration()
        }
        loginText.setOnClickListener {
            gotoLogin()
        }
        return view
    }


    private fun completeRegistration() {
        val email = emailEditText.text.toString()
        val password = passwordEditText.text.toString()

        // Validate email and password
        if (validateInput(email, password)) {
            val result = credentialsManager.register(email, password)
            Toast.makeText(requireContext(), result, Toast.LENGTH_LONG).show()

            if (result == "User Registered Successfully.") {
                gotoLogin()
            }
        }
    }

    private fun gotoLogin() {
        val fragment = LoginFragment()
        parentFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, fragment)
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
            isValid = false
        } else {
            passwordInputLayout.error = null
        }
        return isValid
    }

}
