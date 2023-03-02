package com.acs.ahadumonitor.feature_users.data.source.local.dao

import androidx.room.*
import gov.et.ets.feature_users.domain.model.User
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {
    @Query("SELECT * FROM User")
    fun getUsers(): Flow<List<User>>

    @Query("SELECT * FROM User WHERE id = :id")
    suspend fun getUserById(id: Int): User?

    @Query("UPDATE User SET status = :status WHERE ip = :ip")
    suspend fun updateHost(ip: String, status: String)

    @Query("UPDATE User SET status = :status")
    suspend fun updateAllHost(status: String)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user: User)

    @Delete
    suspend fun deleteUser(user: User)
}