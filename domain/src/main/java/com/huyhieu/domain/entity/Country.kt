package com.huyhieu.domain.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class Country(
    val code: String = "",
    val name: String = "",
    val flag: String = "",
) : Parcelable