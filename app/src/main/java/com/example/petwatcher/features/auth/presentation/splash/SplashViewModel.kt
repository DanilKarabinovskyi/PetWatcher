package com.example.petwatcher.features.auth.presentation.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.petwatcher.core.api.Resource
import com.example.petwatcher.core.presentation.Screen
import com.example.petwatcher.core.presentation.UiEvent
import com.example.petwatcher.features.auth.domain.use_case.AuthenticateUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
) : ViewModel() {

    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    init {
        viewModelScope.launch {
            delay(2000)
            _eventFlow.emit(
                UiEvent.Navigate(Screen.LoginScreen.route)
            )
        }
    }
}