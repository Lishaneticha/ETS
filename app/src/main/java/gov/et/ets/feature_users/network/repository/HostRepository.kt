package gov.et.ets.feature_users.network.repository

import gov.et.ets.feature_users.domain.model.User


interface HostRepository {
    suspend fun postPing(urls: List<String>): List<User>
}