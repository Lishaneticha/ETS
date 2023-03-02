package gov.et.ets.feature_users.data.repository

import com.acs.ahadumonitor.feature_users.data.source.local.dao.UserDao
import gov.et.ets.feature_users.domain.model.User
import gov.et.ets.feature_users.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val dao: UserDao
): UserRepository {
    override fun getUsers(): Flow<List<User>> {
        return dao.getUsers()
    }

    override suspend fun getUserById(id: Int): User? {
       return dao.getUserById(id)
    }

    override suspend fun insertUser(user: User) {
        dao.insertUser(user)
    }

    override suspend fun updateHost(ip: String, status: String) {
        dao.updateHost(ip, status)
    }

    override suspend fun updateAllHost(status: String) {
        dao.updateAllHost(status)
    }

    override suspend fun deleteUser(user: User) {
        dao.deleteUser(user)
    }
}