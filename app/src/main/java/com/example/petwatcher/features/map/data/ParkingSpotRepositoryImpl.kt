package com.example.petwatcher.features.map.data

import com.example.petwatcher.features.map.domain.model.PetSpot
import com.example.petwatcher.features.map.domain.repository.PetsSpotsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class PetsSpotRepositoryImpl(
    private val dao: PetSpotDao
): PetsSpotsRepository {

    override suspend fun insertPetSpot(spot: PetSpot) {
        dao.insertParkingSpot(spot.toParkingSpotEntity())
    }

    override suspend fun deletePetSpot(spot: PetSpot) {
        dao.deleteParkingSpot(spot.toParkingSpotEntity())
    }

    override fun getPetsSpots(): Flow<List<PetSpot>> {
        return dao.getParkingSpots().map { spots ->
            spots.map { it.toParkingSpot() }
        }
    }
}