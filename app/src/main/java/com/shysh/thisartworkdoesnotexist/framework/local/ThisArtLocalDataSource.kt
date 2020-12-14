package com.shysh.thisartworkdoesnotexist.framework.local

import android.content.Context
import com.shysh.thisartworkdoesnotexist.core.data.LocalDataSource
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class ThisArtLocalDataSource @Inject constructor(
   private val context: Context
) : LocalDataSource {

    override fun saveToCache(bytes: ByteArray): String {
        return "test"
    }
}