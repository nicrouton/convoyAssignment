package edu.temple.convoy

import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
/*
For user registration:
“action” : “REGISTER”
“username” : A string representing the user’s chosen username
“firstname” : A string representing the user’s first name
“lastname” : A string representing the user’s last name
“password” : An alphanumeric string representing the user’s password
*/

/*
For user login:
“action” : “LOGIN”
“username” : A string representing the user’s chosen username
“password” : An alphanumeric string representing the user’s password
 */

/*
For user logout:
“action” : “LOGOUT”
“username” : A string representing the user’s chosen username
“session_key” : The session key received from the last successful log in

 */
interface MyApiService {

    @FormUrlEncoded
    @POST("account.php") // replace "endpoint" with your actual endpoint
    suspend fun registerUser(@Field("action") action: String,
                             @Field("username") username: String,
                             @Field("firstname") firstname: String,
                             @Field("lastname") lastname: String,
                             @Field("password") password: String) : Response<ResponseData> // Adjust the return type accordingly

    @FormUrlEncoded
    @POST("account.php")
    suspend fun loginUser(
        @Field("action") action: String,
        @Field("username") username: String,
        @Field("password") password: String
    ): Response<ResponseData> // Adjust the return type accordingly

    @FormUrlEncoded
    @POST("account.php")
    suspend fun logoutUser(
        @Field("action") action: String,
        @Field("username") username: String,
        @Field("session_key") sessionKey: String
    ): Response<ResponseData> // Adjust the return type accordingly
    /*fun postData(@Body requestBody: RequestRegistration): Call<ResponseData> // replace MyResponse with the response data class

    // polymorphic versions of postData for REquestLogin and RequestLogout
    fun postData(@Body requestBody: RequestUserLogin): Call<ResponseData>
    fun postData(@Body requestBody: RequestLogout): Call<ResponseData>*/

}