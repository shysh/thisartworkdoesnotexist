package com.shysh.thisartworkdoesnotexist.ui.home

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.shysh.thisartworkdoesnotexist.core.domain.NoArtObject
import com.shysh.thisartworkdoesnotexist.framework.ThisArtRepository
import com.shysh.thisartworkdoesnotexist.ui.common.live_data.Event
import com.shysh.thisartworkdoesnotexist.ui.common.live_data.MutableLiveDataWithActiveCallback
import kotlinx.coroutines.launch

class HomeViewModel @ViewModelInject constructor(
    private val repository: ThisArtRepository,
    @Assisted private val savedStateHandle: SavedStateHandle
) : ViewModel() {


    private val _artInstance = MutableLiveDataWithActiveCallback<NoArtObject>{
        if(it.value == null){
            loadArt()
        }
    }


    private val _text = MutableLiveData<String>().apply {
        value = "This is home Fragment"
    }

    val text: LiveData<String> = _text

    val artObject = _artInstance.map {
        Event(it, handleAlways = true)
    }

    private fun loadArt(){

        viewModelScope.launch {
            _artInstance.value = repository.loadArt(System.currentTimeMillis())
        }
    }
}