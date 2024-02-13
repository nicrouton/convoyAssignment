package edu.temple.convoy

/*
Data class for user login POST request
For user login:
“action” : “LOGIN”
“username” : A string representing the user’s chosen username
“password” : An alphanumeric string representing the user’s password
 */
data class RequestUserLogin(val action: String, val username: String, val password: String)
