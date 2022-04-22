package com.example.petwatcher.features.map.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class PetSpotEntity(
    val lat: Double,
    val lng: Double,
    @PrimaryKey val id: Int? = null
)
