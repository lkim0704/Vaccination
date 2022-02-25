package com.example.diseasesh

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Vaccination(
    var country : String,
    var timeline : Map<String, Long>
): Parcelable
