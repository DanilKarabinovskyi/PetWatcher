package com.example.petwatcher.features.map.presentation

import com.example.petwatcher.features.map.domain.model.PetSpot
import com.google.maps.android.compose.MapProperties

data class MapState(
    val properties: MapProperties = MapProperties(),
    val petSpots: List<PetSpot> = emptyList(),
    val isFalloutMap: Boolean = false
)
