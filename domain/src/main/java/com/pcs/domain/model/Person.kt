package com.pcs.domain.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Person(
    val createdAt: String = "",
    val name: String = "",
    val avatar: String = "",
    val city: String = "",
    val country: String = "",
    val county: String = "",
    @SerializedName("address_no")
    val addressNo: String = "",
    val street: String = "",
    @SerializedName("zip_code")
    val zipCode: String = "",
    val id: String = ""
) : Serializable