package edu.temple.convoy

/*
Data class for registration POST request to server

For user registration:
“action” : “REGISTER”
“username” : A string representing the user’s chosen username
“firstname” : A string representing the user’s first name
“lastname” : A string representing the user’s last name
“password” : An alphanumeric string representing the user’s password
 */

data class RequestRegistration(val action: String, val username: String, val firstname: String, val lastname: String, val password: String)
