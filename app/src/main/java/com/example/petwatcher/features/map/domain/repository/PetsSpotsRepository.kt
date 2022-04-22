package com.example.petwatcher.features.map.domain.repository

import com.example.petwatcher.features.map.domain.model.PetSpot
import kotlinx.coroutines.flow.Flow

interface PetsSpotsRepository {

    suspend fun insertPetSpot(spot: PetSpot)

    suspend fun deletePetSpot(spot: PetSpot)

    fun getPetsSpots(): Flow<List<PetSpot>>
}