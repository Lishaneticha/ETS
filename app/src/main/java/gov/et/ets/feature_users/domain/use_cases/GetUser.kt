package gov.et.ets.feature_users.domain.use_cases

import gov.et.ets.feature_users.domain.model.User
import gov.et.ets.feature_users.domain.repository.UserRepository
import javax.inject.Inject

class GetUser @Inject constructor(
    private val repository: UserRepository
) {
    suspend operator fun invoke(id: Int): User? {
        return repository.getUserById(id)
    }
}