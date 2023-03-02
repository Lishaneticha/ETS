package com.acs.ahadumonitor.feature_users.presentation.home

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import gov.et.ets.feature_users.domain.use_cases.DeleteUser
import gov.et.ets.feature_users.domain.use_cases.GetUsers
import gov.et.ets.feature_users.domain.use_cases.UpdateHost
import gov.et.ets.feature_users.domain.use_cases.UpdateAllHost
import gov.et.ets.feature_users.network.repository.HostRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import gov.et.ets.feature_users.presentation.home.HomeEvent
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val deleteUser: DeleteUser,
    private val hostRepository: HostRepository,
    private val updateHost: UpdateHost,
    private val updateAllHost: UpdateAllHost,
    getUsers: GetUsers
): ViewModel() {

    private val _state = mutableStateOf(HomeState())
    val state: State<HomeState> = _state
    private val _isLoading = mutableStateOf(false)
    val isLoading: State<Boolean> = _isLoading
    private val _noResult = mutableStateOf(false)
    val noResult: State<Boolean> = _noResult
    private val _emptyRequest = mutableStateOf(false)
    val emptyRequest: State<Boolean> = _emptyRequest

    init {
        getUsers().onEach { users ->
            _state.value = state.value.copy(
                users = users
            )
        }.launchIn(viewModelScope)
    }

    fun onEvent(event: HomeEvent) {
        when (event) {
            is HomeEvent.DeleteUser -> {
                viewModelScope.launch {
                    deleteUser(event.user)
                }
            }
            is HomeEvent.PingHosts -> {
                viewModelScope.launch {
                    if(event.hosts.isNotEmpty()){
                        _isLoading.value = true
                        _emptyRequest.value = false
                        val result = hostRepository.postPing(event.hosts)

                        if (result.isEmpty()) {
                            _noResult.value = true
                            updateAllHost("unknown")
                        } else {
                            _noResult.value = false
                            result.forEach {
                                updateHost(it.ip, it.status)
                            }
                        }
                        _isLoading.value = false
                    }else{
                        _emptyRequest.value = true
                    }
                }
            }
        }
    }
}