package com.example.diseasesh

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Worldwide(
    var updated : Long,
    var cases : Long,
    var todayCases : Long,
    var deaths : Long,
    var todayDeaths : Long,
    var recovered : Long,
    var todayRecovered : Long,
    var activity : Long,
    var critical : Long,
    var casesPerMillion : Long,
    var deathsPerOneMillion : Float,
    var tests : Long,
    var testsPerOneMillion : Float,
    var population : Long,
    var oneCasePerPeople : Int,
    var oneDeathPerPeople : Int,
    var oneTestPerPeople : Int,
    var activePerOneMillion : Float,
    var recoveredPerOneMillion : Float,
    var criticalPerOneMillion : Float,
    var affectedCountries : Int
): Parcelable
