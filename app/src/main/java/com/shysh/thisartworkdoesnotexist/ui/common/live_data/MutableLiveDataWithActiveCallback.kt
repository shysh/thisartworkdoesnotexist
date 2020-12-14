package com.shysh.thisartworkdoesnotexist.ui.common.live_data

import androidx.lifecycle.MutableLiveData
import com.shysh.thisartworkdoesnotexist.ui.common.ParametricAction

import java.util.concurrent.atomic.AtomicBoolean

open class MutableLiveDataWithActiveCallback<T> : MutableLiveData<T> {

    private val mTriggerAllOnActiveCallbacks: Boolean
    private val mOnFirstActiveCallback: ParametricAction<MutableLiveData<T>>
    private val mIsFirstActiveCallbackFired = AtomicBoolean(false)

    constructor(
        triggerAllOnActiveCallbacks: Boolean = false,
        onFirstActive: ParametricAction<MutableLiveData<T>>
    ) {
        this.mTriggerAllOnActiveCallbacks = triggerAllOnActiveCallbacks
        this.mOnFirstActiveCallback = onFirstActive
    }

    constructor(
        triggerAllOnActiveCallbacks: Boolean = false,
        initialValue: T? = null,
        onFirstActiveCallback: ParametricAction<MutableLiveData<T>>
    ) : super(initialValue) {
        this.mTriggerAllOnActiveCallbacks = triggerAllOnActiveCallbacks
        this.mOnFirstActiveCallback = onFirstActiveCallback
    }

    override fun onActive() {
        super.onActive()
        if (mTriggerAllOnActiveCallbacks || !mIsFirstActiveCallbackFired.get()) {
            mIsFirstActiveCallbackFired.compareAndSet(mIsFirstActiveCallbackFired.get(), true)
            mOnFirstActiveCallback.invoke(this)
        }
    }

}

