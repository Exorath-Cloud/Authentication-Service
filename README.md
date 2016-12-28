# Authentication-Service
The Authentication-Service serves and validates user authetication.

##Endpoints
###/auth/ [POST]
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

###/auth/register [POST]
###Creates a new userdata entry with a username, password and email

**Reuqest Body**:
```json
{  
   "username":"value",
   "email":"value",
   "password":"value"
}
```

**Response Body**:
```json
{  
   "status": 200 / 400,
   "errorMessage":"value"
}
```
