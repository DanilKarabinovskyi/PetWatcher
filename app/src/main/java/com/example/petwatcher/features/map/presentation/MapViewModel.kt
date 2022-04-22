package com.example.petwatcher.features.map.presentation

import android.content.SharedPreferences
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.petwatcher.core.presentation.Constants
import com.example.petwatcher.features.map.domain.model.PetSpot
import com.example.petwatcher.features.map.domain.repository.PetsSpotsRepository
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MapViewModel @Inject constructor(
    private val repository: PetsSpotsRepository,
    private val sharedPreferences: SharedPreferences
): ViewModel() {

    var state by mutableStateOf(MapState())

    private val _userName = mutableStateOf("")
    val userName: State<String> = _userName

    private val _cameraPosition = mutableStateOf(CameraPosition.fromLatLngZoom(LatLng(49.93, 26.70),11f))
    val cameraPosition: State<CameraPosition> = _cameraPosition

    private val _petPhotoPath = mutableStateOf("")
    val petPhotoPath: State<String> = _petPhotoPath

    init {
        viewModelScope.launch {
            repository.getPetsSpots().collectLatest { spots ->
                state = state.copy(
                    petSpots = spots
                )
                _cameraPosition.value = CameraPosition.fromLatLngZoom(LatLng(spots.last().lat, spots.last().lng),11f)
            }
        }
    }


    init {
        _userName.value = sharedPreferences.getString(Constants.SHARED_PREF_NAME, "").toString()
        _petPhotoPath.value = sharedPreferences.getString(Constants.SHARED_PREF_PHOTO_PATH,"").toString()
    }
    fun onEvent(event: MapEvent) {
        when(event) {
            is MapEvent.ToggleFalloutMap -> {

            }
            is MapEvent.OnMapLongClick -> {
                viewModelScope.launch {
                    repository.insertPetSpot(PetSpot(
                        event.latLng.latitude,
                        event.latLng.longitude
                    ))
                    if(state.petSpots.size>9){
                        repository.deletePetSpot(state.petSpots[0])
                    }
                }
            }
            is MapEvent.OnInfoWindowLongClick -> {
                viewModelScope.launch {
                    repository.deletePetSpot(event.spot)
                }
            }
        }
    }


}