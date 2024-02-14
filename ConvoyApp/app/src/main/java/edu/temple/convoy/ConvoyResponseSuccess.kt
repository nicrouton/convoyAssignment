package edu.temple.convoy

import com.google.gson.annotations.SerializedName

data class ConvoyResponseSuccess(
    @SerializedName("status") val status: String?,
    @SerializedName("convoy_id") val sessionKey: String?,
    @SerializedName("firstname") val firstname: String?,
    @SerializedName("lastname") val lastname: String?,
)
