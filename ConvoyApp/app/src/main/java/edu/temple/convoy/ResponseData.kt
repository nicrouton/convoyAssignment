package edu.temple.convoy

import com.google.gson.annotations.SerializedName

/*
Data class for server's response

The response from server will be one of two possible JSON string formats:
Either:
{"status":"SUCCESS", "session_key": "String"} if successful, or
{"status":"ERROR", "message": "String"} if there was an error.
In case of an error, the message string will contain a description of the error encountered

 */
data class ResponseData(
    @SerializedName("status") val status: String?,
    @SerializedName("session_key") val sessionKey: String?
)

