package com.shysh.thisartworkdoesnotexist.framework

import com.shysh.thisartworkdoesnotexist.core.data.LocalDataSource
import com.shysh.thisartworkdoesnotexist.core.data.RemoteDataSource
import com.shysh.thisartworkdoesnotexist.core.data.Repository
import com.shysh.thisartworkdoesnotexist.core.domain.NoArtObject
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

class ThisArtRepository constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource):Repository {

    override suspend fun loadArt(timeStamp:Long): NoArtObject {
        val path = remoteDataSource.getNoArt(timeStamp).let {
            localDataSource.saveToCache(it)
        }
        return NoArtObject(timeStamp, path)
    }
}