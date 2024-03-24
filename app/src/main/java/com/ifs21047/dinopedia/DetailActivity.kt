package com.ifs21047.dinopedia

import android.content.Intent
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
            intent.getParcelableExtra(EXTRA_FAMILY, Family::class.java)
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

        binding.buttonDetailDino.setOnClickListener {
            val intentWithData = Intent(this@DetailActivity, DinosaurusActivity::class.java)
            intentWithData.putExtra(DinosaurusActivity.EXTRA_FAMILY, family!!)
            startActivity(intentWithData)
        }
    }

    private fun loadData(family: Family) {
        binding.ivDetailLogo.setImageResource(family.logo)
        binding.tvDetailFamily.text = family.name
        binding.tvDetailDescription.text = family.description
        binding.tvDetailKlasifikasi.text = family.klasifikasi
        binding.tvDetailPeriode.text = family.periode
        binding.tvDetailHabitat.text = family.habitat
        binding.tvDetailPerilaku.text = family.perilaku

    }

    private fun showSelectedDinosaurus(dinosaurus: Dinosaurus) {
        val intentWithData = Intent(
            this@DetailActivity,
            DetailDinosaurusActivity::class.java
        )
        intentWithData.putExtra(DetailDinosaurusActivity.EXTRA_DINO, dinosaurus)
        startActivity(intentWithData)
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
        const val EXTRA_FAMILY = "extra_family"
    }
}