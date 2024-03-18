package com.ifs21047.dinopedia

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
@Parcelize
data class Family(
    var name: String,
    var logo: Int,
    var description: String,
    var dinosaurus: String,
) : Parcelable