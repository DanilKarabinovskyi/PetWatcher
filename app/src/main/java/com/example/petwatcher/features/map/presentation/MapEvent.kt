package com.example.petwatcher.features.map.presentation

import com.example.petwatcher.features.map.domain.model.PetSpot
import com.google.android.gms.maps.model.LatLng

sealed class MapEvent {
    object ToggleFalloutMap: MapEvent()
    data class OnMapLongClick(val latLng: LatLng): MapEvent()
    data class OnInfoWindowLongClick(val spot: PetSpot): MapEvent()
}
