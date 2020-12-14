package com.shysh.thisartworkdoesnotexist.core.data


interface RemoteDataSource{

    suspend fun getNoArt(timeStamp:Long): ByteArray
}