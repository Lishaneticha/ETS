package gov.et.ets.feature_users.domain.repository

import gov.et.ets.feature_users.domain.model.User
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    fun getUsers(): Flow<List<User>>

    suspend fun getUserById(id: Int): User?

    suspend fun insertUser(user: User)

    suspend fun updateHost(ip: String, status: String)

    suspend fun updateAllHost(status: String)

    suspend fun deleteUser(user: User)
}