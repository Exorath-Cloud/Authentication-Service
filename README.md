# Authentication-Service
The Authentication-Service serves and validates user authetication.

##Endpoints
###/accounts/ [POST]
###Authenticates users by userid, username or email, at least one needs to be valid. This returns a valid access token.

**Reuqest Body**:
```json
{  
   "userid":"value",
   "username":"value",
   "email":"value",
   "password":"value"
}
```

**Response Body**:
 
 If the authentication fails:
```json
{  
   "status":400,
   "errorMessage":"value"
}
```
 
 If the authentication passes:
```json
{  
   "accesstoken":"value",
   "expiry":"value",
   "status":200,
   "errorMessage":"success"
}
```
- accesstoken (String): the non-hashed accesstoken id
- expiry (Date): the date the access token expires
