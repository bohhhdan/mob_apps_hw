package com.example.myapplicationlast

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

class FragmentsActivity : AppCompatActivity() {

    private lateinit var switchFragmentButton: Button
    private val isFragmentA = true
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fragments)

        // Start with the LoginFragment when the activity is first created
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, FragmentA())
                .commit()
        }

        // Switch between fragments when the button is clicked
        switchFragmentButton = findViewById(R.id.switchFragmentButton)
        switchFragmentButton.setOnClickListener {
            val fragment_manager=supportFragmentManager
            val transaction=fragment_manager.beginTransaction()

            if (isFragmentA) {
                transaction.replace(R.id.fragmentContainer, FragmentB())
            } else {
                transaction.replace(R.id.fragmentContainer, FragmentA())
            }

            transaction.commit()
        }
    }
}
