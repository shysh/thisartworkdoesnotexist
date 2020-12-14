package com.shysh.thisartworkdoesnotexist.core.data

interface LocalDataSource {

    fun saveToCache(bytes:ByteArray):String
}