package com.huyhieu.domain.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class User(
    var avatar: Byte? = null,
    var email: String = "",
    var password: String = "",
    var fullName: String = "",
    var birthDay: String = "",
    var phoneNumber: String = "",
    var countryCode: String = "",
) : Parcelable