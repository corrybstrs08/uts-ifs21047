package com.ifs21047.dinopedia

import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.ifs21047.dinopedia.databinding.ActivityDetailBinding


class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding
    private var family: Family? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
       family = if (Build.VERSION.SDK_INT >= 33) {
            intent.getParcelableExtra(EXTRA_FAMILY,
                Family::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra(EXTRA_FAMILY)
        }
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        if (family != null) {
            supportActionBar?.title = "Family ${family!!.name}"
            loadData(family!!)
        } else {
            finish()
        }

        binding.fabShare.setOnClickListener {
            shareContent()
        }
    }
    private fun loadData(family: Family) {
        binding.ivDetailLogo.setImageResource(family.logo)
        binding.tvDetailFamily.text = family.name
        binding.tvDetailDescription.text = family.description
        binding.tvDetailDinosaurus.text = family.dinosaurus
    }
    private fun shareContent() {
        val content = "Your shared content here"

        val shareIntent = Intent().apply {
            action = Intent.ACTION_SEND
            type = "text/plain"
            putExtra(Intent.EXTRA_TEXT, content)
        }

        // WhatsApp
        val whatsappIntent = Intent().apply {
            action = Intent.ACTION_SEND
            type = "text/plain"
            setPackage("com.whatsapp")
            putExtra(Intent.EXTRA_TEXT, content)
        }

        // Email
        val emailIntent = Intent(Intent.ACTION_SEND).apply {
            type = "message/rfc822"
            putExtra(Intent.EXTRA_SUBJECT, "Subject")
            putExtra(Intent.EXTRA_TEXT, content)
        }

        // Instagram
        val instagramIntent = Intent().apply {
            action = Intent.ACTION_SEND
            type = "image/*"
            setPackage("com.instagram.android")
            putExtra(Intent.EXTRA_TEXT, content)
        }

        // Chrome
        val chromeIntent = Intent().apply {
            action = Intent.ACTION_VIEW
            data = Uri.parse("http://www.example.com")
        }

        // Facebook
        val facebookIntent = Intent().apply {
            action = Intent.ACTION_SEND
            type = "text/plain"
            setPackage("com.facebook.katana")
            putExtra(Intent.EXTRA_TEXT, content)
        }

        // LinkedIn
        val linkedInIntent = Intent().apply {
            action = Intent.ACTION_SEND
            type = "text/plain"
            setPackage("com.linkedin.android")
            putExtra(Intent.EXTRA_TEXT, content)
        }

        // Telegram
        val telegramIntent = Intent().apply {
            action = Intent.ACTION_SEND
            type = "text/plain"
            setPackage("org.telegram.messenger")
            putExtra(Intent.EXTRA_TEXT, content)
        }

        // Google Drive
        val driveIntent = Intent().apply {
            action = Intent.ACTION_SEND
            type = "text/plain"
            setPackage("com.google.android.apps.docs")
            putExtra(Intent.EXTRA_TEXT, content)
        }

        // Create chooser
        val chooserIntent = Intent.createChooser(shareIntent, "Share to")
        chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, arrayOf(whatsappIntent, emailIntent, instagramIntent, chromeIntent, facebookIntent, linkedInIntent, telegramIntent, driveIntent))

        // Start activity
        if (shareIntent.resolveActivity(packageManager) != null) {
            startActivity(chooserIntent)
        }
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }
    companion object {
        const val EXTRA_FAMILY= "extra_family"
    }
}