package edu.temple.convoy

import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.Call

interface MyApiService {

    @POST("https://kamorris.com/lab/convoy/account.php") // replace "endpoint" with your actual endpoint
    fun postData(@Body requestBody: RequestRegistration): Call<ResponseData> // replace MyResponse with the response data class
}