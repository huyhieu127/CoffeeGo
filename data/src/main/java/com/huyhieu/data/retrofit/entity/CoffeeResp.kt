package com.huyhieu.data.retrofit.entity

import com.google.gson.annotations.SerializedName

data class CoffeeResp(
    @SerializedName("description")
    val description: String = "",
    @SerializedName("flavor_profile")
    val flavorProfile: List<String> = listOf(),
    @SerializedName("grind_option")
    val grindOption: List<String> = listOf(),
    @SerializedName("_id")
    val idStr: String = "",
    @SerializedName("id")
    val id: Int = 0,
    @SerializedName("image_url")
    val imageUrl: String = "",
    @SerializedName("name")
    val name: String = "",
    @SerializedName("price")
    val price: Double = 0.0,
    @SerializedName("region")
    val region: String = "",
    @SerializedName("roast_level")
    val roastLevel: Int = 0,
    @SerializedName("weight")
    val weight: Int = 0
) : Resp

interface Resp