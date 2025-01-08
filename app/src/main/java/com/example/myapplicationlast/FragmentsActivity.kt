package com.example.myapplicationlast

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

class FragmentsActivity : AppCompatActivity() {

    private lateinit var switchFragmentButton: Button
    private val isFragmentLogin = true
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fragments)

        // Start with the LoginFragment when the activity is first created
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, LoginFragment())
                .commit()
        }

        // Switch between fragments when the button is clicked
        switchFragmentButton = findViewById(R.id.switchFragmentButton)
        switchFragmentButton.setOnClickListener {
            val currentFragment = supportFragmentManager.findFragmentById(R.id.fragmentContainer)

            if (currentFragment is LoginFragment) {
                showFragment(RegisterFragment())
            } else {
                showFragment(LoginFragment())
            }
        }
    }

    private fun showFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, fragment)
            .commit()
    }
}
