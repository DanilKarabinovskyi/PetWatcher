package com.example.petwatcher.features.map.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [PetSpotEntity::class],
    version = 1
)
abstract class PetSpotDatabase: RoomDatabase() {

    abstract val dao: PetSpotDao
}