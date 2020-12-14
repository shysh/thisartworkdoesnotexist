package com.shysh.thisartworkdoesnotexist.framework.remote

import retrofit2.http.GET

interface ThisArtworkDoesNotExistApi {

    @GET
    suspend fun getArt(): ByteArray
}