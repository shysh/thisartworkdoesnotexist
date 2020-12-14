package com.shysh.thisartworkdoesnotexist.framework.remote

import com.shysh.thisartworkdoesnotexist.core.data.RemoteDataSource

class ThisArtRemoteDataSource (private val api:ThisArtworkDoesNotExistApi): RemoteDataSource {
    override suspend fun getNoArt(timeStamp: Long): ByteArray  = api.getArt()
}