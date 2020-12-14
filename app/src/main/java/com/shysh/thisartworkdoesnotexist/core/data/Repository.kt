package com.shysh.thisartworkdoesnotexist.core.data

import com.shysh.thisartworkdoesnotexist.core.domain.NoArtObject

interface  Repository {
    suspend fun loadArt(timeStamp: Long): NoArtObject
}