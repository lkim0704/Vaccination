package com.example.diseasesh

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.diseasesh.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.time.LocalDate
import java.time.format.DateTimeFormatter


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var currentTime : String
    private lateinit var vaccinationList : List<Vaccination>
    private lateinit var worldwidelist : Worldwide
    lateinit var adapter : VaccinationAdapter

    companion object{
        val TAG = "TAG"
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val current : LocalDate = LocalDate.now()
        val formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy")
        currentTime = current.format(formatter)

        val vaccineApi = RetrofitHelper.getInstance().create(Covid19Interface::class.java)
        val vaccineCall = vaccineApi.getVaccinations(5)

        binding.textViewMainTitle.text = "Today $currentTime : Worldwide Statistics"
        binding.textViewMainCases.text = "Cases : "
        binding.textViewMainDeaths.text = "Deaths : "


        val worldwideApi = RetrofitHelper.getInstance().create(WorldwideInterface::class.java)
        val worldwideCall = worldwideApi.getWorldwide()

        worldwideCall.enqueue(object : Callback<Worldwide> {
            override fun onResponse(
                call: Call<Worldwide>,
                response: Response<Worldwide>
            ) {
                worldwidelist = response.body()!!
                binding.textViewMainCasesNumber.text = worldwidelist.cases.toString()
                binding.textViewMainDeathsNumber.text = worldwidelist.deaths.toString()
                Log.d(TAG, "onResponse: ${worldwidelist.cases}, ${worldwidelist.deaths}")
            }

            override fun onFailure(call: Call<Worldwide>, t: Throwable) {
                Log.d(TAG, "onFailure: ${t.message}")
            }

        }
        )


        vaccineCall.enqueue(object : Callback<List<Vaccination>> {
            override fun onResponse(
                call: Call<List<Vaccination>>,
                response: Response<List<Vaccination>>
            ) {
                vaccinationList = response.body() ?: listOf<Vaccination>()
                adapter = VaccinationAdapter(vaccinationList)
                binding.recycleViewDiseaseSHCountryList.adapter = adapter
                binding.recycleViewDiseaseSHCountryList.layoutManager = LinearLayoutManager(this@MainActivity, LinearLayoutManager.HORIZONTAL, false)
            }

            override fun onFailure(call: Call<List<Vaccination>>, t: Throwable) {
                Log.d(TAG, "onFailure: ${t.message}")
            }

        }
        )
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.option_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_name -> {
                adapter.dataSet = adapter.dataSet.sortedBy { it.country }
                adapter.notifyDataSetChanged()
                true
            }
            R.id.menu_vaccine -> {
                adapter.dataSet = adapter.dataSet.sortedBy { it.timeline.values.last() }
                adapter.notifyDataSetChanged()
                true
            }
            R.id.menu_increase -> {
                adapter.dataSet = adapter.dataSet.sortedBy { it.timeline.values.last()-it.timeline.values.first() }
                adapter.notifyDataSetChanged()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

}