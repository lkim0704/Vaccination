package com.example.diseasesh

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.diseasesh.databinding.ActivityVaccinationDetailBinding

class VaccinationDetail : AppCompatActivity() {

    private lateinit var binding: ActivityVaccinationDetailBinding

    companion object{
        val EXTRA_VACCINATION = "The vaccination"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityVaccinationDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val vaccination = intent.getParcelableExtra<Vaccination>(EXTRA_VACCINATION)
        binding.textViewVaccinationDetailCountryName.text = vaccination?.country
        binding.textViewVaccinationDetailKey1.text = vaccination?.timeline?.keys?.elementAt(0)
        binding.textViewVaccinationDetailKey2.text = vaccination?.timeline?.keys?.elementAt(1)
        binding.textViewVaccinationDetailKey3.text = vaccination?.timeline?.keys?.elementAt(2)
        binding.textViewVaccinationDetailKey4.text = vaccination?.timeline?.keys?.elementAt(3)
        binding.textViewVaccinationDetailKey5.text = vaccination?.timeline?.keys?.elementAt(4)
        binding.textViewVaccinationDetailNumber1.text = vaccination?.timeline?.values?.elementAt(0).toString()
        binding.textViewVaccinationDetailNumber2.text = vaccination?.timeline?.values?.elementAt(1).toString()
        binding.textViewVaccinationDetailNumber3.text = vaccination?.timeline?.values?.elementAt(2).toString()
        binding.textViewVaccinationDetailNumber4.text = vaccination?.timeline?.values?.elementAt(3).toString()
        binding.textViewVaccinationDetailNumber5.text = vaccination?.timeline?.values?.elementAt(4).toString()

        binding.buttonVaccinationDetailBack.text = "Back"
        binding.buttonVaccinationDetailBack.setOnClickListener {
            val mainActivityIntent = Intent(this, MainActivity::class.java)
            this.startActivity(mainActivityIntent)
        }

    }
}