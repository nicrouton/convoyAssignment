package edu.temple.convoy

/* Data class for logout POST request

For user logout:
“action” : “LOGOUT”
“username” : A string representing the user’s chosen username
“session_key” : The session key received from the last successful log in
 */

data class RequestLogout(val action: String, val username: String, val sessionKey: String)
