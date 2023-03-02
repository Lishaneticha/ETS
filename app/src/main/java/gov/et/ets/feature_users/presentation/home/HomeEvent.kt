package gov.et.ets.feature_users.presentation.home

import gov.et.ets.feature_users.domain.model.User

sealed class HomeEvent {
    data class DeleteUser(val user: User): HomeEvent()
    data class PingHosts(val hosts: List<String>): HomeEvent()
}
