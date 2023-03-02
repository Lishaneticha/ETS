package gov.et.ets.feature_users.domain.use_cases

import gov.et.ets.feature_users.domain.repository.UserRepository
import javax.inject.Inject

class UpdateHost @Inject constructor(
    private val repository: UserRepository
) {
    suspend operator fun invoke(ip: String, status: String) {
        repository.updateHost(ip, status)
    }
}