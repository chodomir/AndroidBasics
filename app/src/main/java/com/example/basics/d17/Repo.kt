package com.example.basics.d17

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Repo(
    val name: String,
    val description: String?,
    @Json(name = "private") val isPrivate: Boolean
) {
    override fun toString(): String {
        return name
    }
}