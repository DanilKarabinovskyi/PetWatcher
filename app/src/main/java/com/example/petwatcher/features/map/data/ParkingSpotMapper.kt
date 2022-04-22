package com.example.petwatcher.features.map.data

import com.example.petwatcher.features.map.domain.model.PetSpot

fun PetSpotEntity.toParkingSpot(): PetSpot {
    return PetSpot(
        lat = lat,
        lng = lng,
        id = id
    )
}

fun PetSpot.toParkingSpotEntity(): PetSpotEntity {
    return PetSpotEntity(
        lat = lat,
        lng = lng,
        id = id
    )
}