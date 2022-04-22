package com.example.petwatcher.features.profile.presentation

import android.content.SharedPreferences
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.petwatcher.core.domain.PasswordTextFieldState
import com.example.petwatcher.core.domain.StandardTextFieldState
import com.example.petwatcher.core.presentation.Constants
import com.example.petwatcher.core.presentation.UiEvent
import com.example.petwatcher.features.auth.domain.use_case.RegisterUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val sharedPreferences: SharedPreferences
    ) : ViewModel() {

    private val _userName = mutableStateOf("")
    val userName: State<String> = _userName

    private val _petPhotoPath = mutableStateOf("")
    val petPhotoPath: State<String> = _petPhotoPath

    init {
        _userName.value = sharedPreferences.getString(Constants.SHARED_PREF_NAME, "").toString()
        viewModelScope.launch {
            _petPhotoPath.value = sharedPreferences.getString(Constants.SHARED_PREF_PHOTO_PATH,"").toString()
        }
    }
    fun saveName(name:String){
        _userName.value = name
        sharedPreferences.edit()
            .putString(Constants.SHARED_PREF_NAME, name)
            .apply()
    }

    fun savePetPhoto(path:String){
        _petPhotoPath.value = path
        sharedPreferences.edit()
            .putString(Constants.SHARED_PREF_PHOTO_PATH, path)
            .apply()
    }

}