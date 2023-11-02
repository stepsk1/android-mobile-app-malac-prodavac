package com.triforce.malacprodavac.data.remote.users

import com.triforce.malacprodavac.data.remote.Api


interface UsersApi {
    companion object {
       const val BASE_URL="${Api.BASE_URL}/users"
    }
}