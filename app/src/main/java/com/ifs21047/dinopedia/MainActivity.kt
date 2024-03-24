package com.ifs21047.dinopedia

import android.annotation.SuppressLint
import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.ifs21047.dinopedia.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val dataFamilies = ArrayList<Family>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.rvFamilys.setHasFixedSize(false)
        dataFamilies.addAll(getListFamilys())
        showRecyclerList()
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
    private fun getListFamilys(): ArrayList<Family> {
        val dataName =
            resources.getStringArray(R.array.familys_name)
        val dataLogo =
            resources.obtainTypedArray(R.array.familys_logo)
        val dataDescription =
            resources.getStringArray(R.array.familys_description)
        val dataKlasifikasi =
            resources.getStringArray(R.array.familys_klasifikasi)
        val dataPeriode =
            resources.getStringArray(R.array.familys_periode)
        val dataHabitat =
            resources.getStringArray(R.array.familys_habitat)
        val dataPerilaku =
            resources.getStringArray(R.array.familys_perilaku)
        val startIndex =
            resources.getStringArray(R.array.start_index_dino)
        val endIndex =
            resources.getStringArray(R.array.end_index_dino)

        val listFamily = ArrayList<Family>()
        for (i in dataName.indices) {
            // Create a Family object and add it to the list
            val family = Family(
                dataName[i], dataLogo.getResourceId(i, -1), dataDescription[i],
                dataKlasifikasi[i], dataPeriode[i], dataHabitat[i],
                dataPerilaku[i], startIndex[i].toInt(), endIndex[i].toInt(),
            )
            listFamily.add(family)
        }
        return listFamily
    }

    private fun showRecyclerList() {
        if (resources.configuration.orientation ==
            Configuration.ORIENTATION_LANDSCAPE) {
            binding.rvFamilys.layoutManager =
                GridLayoutManager(this, 2)
        } else {
            binding.rvFamilys.layoutManager =
                LinearLayoutManager(this)
        }
        val listFamilyAdapter = ListFamilyAdapter(dataFamilies)
        binding.rvFamilys.adapter = listFamilyAdapter
        listFamilyAdapter.setOnItemClickCallback(object :
            ListFamilyAdapter.OnItemClickCallback {
            override fun onItemClicked(data: Family) {
                showSelectedFamily(data)
            }
        })
    }

    private fun showSelectedFamily(family: Family) {
        val intentWithData = Intent(
            this@MainActivity,
            DetailActivity::class.java
        )
        intentWithData.putExtra(DetailActivity.EXTRA_FAMILY, family)
        startActivity(intentWithData)
    }
    companion object {
        const val EXTRA_DINO = "extra_dinosauru"
    }
}

