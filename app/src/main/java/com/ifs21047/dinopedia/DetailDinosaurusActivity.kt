package com.ifs21047.dinopedia

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.ifs21047.dinopedia.databinding.ActivityDetailDinosaurusBinding


class DetailDinosaurusActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailDinosaurusBinding
    private var dinosaurus: Dinosaurus? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailDinosaurusBinding.inflate(layoutInflater)
        setContentView(binding.root)
        dinosaurus = if (Build.VERSION.SDK_INT >= 33) {
            intent.getParcelableExtra(EXTRA_DINO,
                Dinosaurus::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra(EXTRA_DINO)
        }
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        if (dinosaurus != null) {
            supportActionBar?.title = "Dinosaurus ${dinosaurus!!.name}"
            loadData(dinosaurus!!)
        } else {
            finish()
        }

        binding.buttonDetailFamily.setOnClickListener {
            val intentWithData = Intent(this@DetailDinosaurusActivity, MainActivity::class.java)
            intentWithData.putExtra(MainActivity.EXTRA_DINO, dinosaurus)
            startActivity(intentWithData)
        }
    }
    private fun loadData(dinosaurus: Dinosaurus) {
        binding.ivDetailIcon.setImageResource(dinosaurus.icon)
        binding.tvDetailDino.text = dinosaurus.name
        binding.tvDetailDescription.text = dinosaurus.desc
        binding.tvDetailHabitat.text = dinosaurus.habit
        binding.tvDetailKarakter.text = dinosaurus.karakter
        binding.tvDetailPerilaku.text = dinosaurus.peri
        binding.tvDetailAdaptasi.text = dinosaurus.adap
        binding.tvDetailPeriode.text = dinosaurus.periode
    }

    private fun showSelectedFamily(family: Family) {
        val intentWithData = Intent(
            this@DetailDinosaurusActivity,
            DetailActivity::class.java
        )
        intentWithData.putExtra(DetailActivity.EXTRA_FAMILY, family)
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
        const val EXTRA_DINO = "extra_dinosaurus"
    }
}