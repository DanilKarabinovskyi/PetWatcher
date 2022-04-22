package com.example.petwatcher.features.map.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface PetSpotDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertParkingSpot(spot: PetSpotEntity)

    @Delete
    suspend fun deleteParkingSpot(spot: PetSpotEntity)

    @Query("SELECT * FROM petspotentity")
    fun getParkingSpots(): Flow<List<PetSpotEntity>>
}