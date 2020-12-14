package com.shysh.thisartworkdoesnotexist.ui.common.view_model

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.annotation.MainThread
import androidx.fragment.app.Fragment
import androidx.fragment.app.createViewModelLazy
import androidx.lifecycle.*
import androidx.savedstate.SavedStateRegistryOwner
import kotlin.reflect.KClass


class BaseSavedStateViewModelFactory<VM : ViewModel>(
    owner: SavedStateRegistryOwner,
    defaultArgs: Bundle?,
    private val initializer: (SavedStateHandle) -> VM
) : AbstractSavedStateViewModelFactory(owner, defaultArgs) {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(
        key: String,
        modelClass: Class<T>,
        handle: SavedStateHandle
    ): T {
        return initializer(handle) as T
    }
}


@MainThread
inline fun <reified VM : ViewModel> Fragment.savedStateViewModels(
    noinline savedStateRegistryOwnerProducer: () -> SavedStateRegistryOwner = { this },
    noinline viewModelStoreOwnerProducer: () -> ViewModelStoreOwner = { this },
    noinline initializer: (SavedStateHandle) -> VM
) = createViewModelLazy(
    viewModelClass = VM::class,
    storeProducer = { viewModelStoreOwnerProducer().viewModelStore },
    factoryProducer = {
        BaseSavedStateViewModelFactory(
            savedStateRegistryOwnerProducer(),
            arguments,
            initializer
        )
    }
)

@MainThread
inline fun <reified VM : ViewModel> Fragment.savedStateKeyedViewModels(
    noinline keyProducer: () -> String,
    noinline savedStateRegistryOwnerProducer: () -> SavedStateRegistryOwner = { this },
    noinline ownerProducer: () -> ViewModelStoreOwner = { this },
    noinline initializer: (SavedStateHandle) -> VM
) = createViewModelKeyedLazy(
    VM::class,
    keyProducer,
    { ownerProducer().viewModelStore },
    { BaseSavedStateViewModelFactory(savedStateRegistryOwnerProducer(), arguments, initializer) }
)


@MainThread
fun <VM : ViewModel> Fragment.createViewModelKeyedLazy(
    viewModelClass: KClass<VM>,
    keyProducer: () -> String,
    storeProducer: () -> ViewModelStore,
    factoryProducer: () -> ViewModelProvider.Factory
): Lazy<VM> {
    return ViewModelKeyedLazy(viewModelClass, keyProducer, storeProducer, factoryProducer)
}

@MainThread
inline fun <reified VM : ViewModel> ComponentActivity.savedStateViewModels(
    noinline initializer: (SavedStateHandle) -> VM
): Lazy<VM> {
    return ViewModelLazy(
        VM::class,
        { viewModelStore },
        { BaseSavedStateViewModelFactory(this, intent?.extras, initializer) })
}


class ViewModelKeyedLazy<VM : ViewModel>(
    private val viewModelClass: KClass<VM>,
    private val keyProducer: () -> String,
    private val storeProducer: () -> ViewModelStore,
    private val factoryProducer: () -> ViewModelProvider.Factory
) : Lazy<VM> {
    private var cached: VM? = null

    override val value: VM
        get() {
            val viewModel = cached
            return if (viewModel == null) {
                val key = keyProducer()
                val factory = factoryProducer()
                val store = storeProducer()
                ViewModelProvider(store, factory).get(key, viewModelClass.java).also {
                    cached = it
                }
            } else {
                viewModel
            }
        }

    override fun isInitialized() = cached != null
}