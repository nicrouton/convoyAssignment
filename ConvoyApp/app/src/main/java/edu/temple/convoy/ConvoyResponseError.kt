package edu.temple.convoy

import com.google.gson.annotations.SerializedName

data class ConvoyResponseError(
    @SerializedName("status") val status: String?,
    @SerializedName("message") val message: String?
)
