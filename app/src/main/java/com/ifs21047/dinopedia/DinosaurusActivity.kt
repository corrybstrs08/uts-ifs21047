package com.ifs21047.dinopedia

import android.annotation.SuppressLint
import android.content.Intent
import android.content.res.Configuration
import android.os.Build
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.ifs21047.dinopedia.databinding.ActivityDinosaurusBinding

class DinosaurusActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDinosaurusBinding
    private val dataDinosaurus = ArrayList<Dinosaurus>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDinosaurusBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.rvDinosaurus.setHasFixedSize(false)
        dataDinosaurus.addAll(getListDinosaurus())
        showRecyclerList()

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_about -> {
                val intent = Intent(this, AboutActivity::class.java)
                startActivity(intent)
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

    @SuppressLint("Recycle")
    private fun getListDinosaurus(): ArrayList<Dinosaurus> {
        val family = if (Build.VERSION.SDK_INT >= 33) {
            intent.getParcelableExtra(EXTRA_FAMILY, Family::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra(EXTRA_FAMILY)
        }

        val dataName =
            resources.getStringArray(R.array.dinos_name)
        val dataIcon =
            resources.obtainTypedArray(R.array.dinos_icon)
        val dataDescription =
            resources.getStringArray(R.array.dinos_desc)
        val dataHabitat =
            resources.getStringArray(R.array.dinos_habitat)
        val dataKarakter =
            resources.getStringArray(R.array.dinos_karakter)
        val dataPerilaku =
            resources.getStringArray(R.array.dinos_perilaku)
        val dataAdaptasi =
            resources.getStringArray(R.array.dinos_adaptasi)
        val dataPeriode =
            resources.getStringArray(R.array.dinos_periode)

        val startIndex = family?.startIndex
        val endIndex = family?.endIndex

        val listDinosaurus = ArrayList<Dinosaurus>()
        for (i in startIndex!! .. endIndex!!) {
            val dinosaurus = Dinosaurus(
                dataName[i], dataIcon.getResourceId(i, -1), dataDescription[i],
                dataHabitat[i], dataKarakter[i], dataPerilaku[i], dataAdaptasi[i], dataPeriode[i]
            )
            listDinosaurus.add(dinosaurus)
        }
        return listDinosaurus
    }

    private fun showRecyclerList() {
        if (resources.configuration.orientation ==
            Configuration.ORIENTATION_LANDSCAPE) {
            binding.rvDinosaurus.layoutManager =
                GridLayoutManager(this, 2)
        } else {
            binding.rvDinosaurus.layoutManager =
                LinearLayoutManager(this)
        }

        val listDinosaurusAdapter = ListDinosaurusAdapter(dataDinosaurus)
        binding.rvDinosaurus.adapter = listDinosaurusAdapter
        listDinosaurusAdapter.setOnItemClickCallback(object :
            ListDinosaurusAdapter.OnItemClickCallback {
            override fun onItemClicked(data: Dinosaurus) {
                showSelectedDinosaurus(data)
            }
        })
    }

    private fun showSelectedDinosaurus(dinosaurus: Dinosaurus) {
        val intentWithData = Intent(this@DinosaurusActivity,
            DetailDinosaurusActivity::class.java)
        intentWithData.putExtra(DetailDinosaurusActivity.EXTRA_DINO, dinosaurus)
        startActivity(intentWithData)
    }

    companion object {
        const val EXTRA_FAMILY = "extra_family"
    }
}
