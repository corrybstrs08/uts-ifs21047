package com.ifs21047.dinopedia

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ifs21047.dinopedia.databinding.ActivityWelcomeBinding

class WelcomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityWelcomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWelcomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Set click listener for Family button
        binding.btnFamily.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }

    }
}