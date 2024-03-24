package com.ifs21047.dinopedia

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
@Parcelize
data class Dinosaurus(
    var name: String,
    var icon: Int,
    var desc: String,
    var karakter: String,
    var habit: String,
    var peri: String,
    var adap: String,
    var periode: String,
//    var startIndex: Int,
//    var endIndex: Int,
) : Parcelable
