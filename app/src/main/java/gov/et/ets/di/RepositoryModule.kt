package gov.et.ets.di

import gov.et.ets.feature_users.network.HostPingService
import gov.et.ets.feature_users.network.model.HostNetworkMapper
import gov.et.ets.feature_users.network.repository.HostRepository
import gov.et.ets.feature_users.network.repository.HostRepository_Impl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideHostRepository(
        hostPingService: HostPingService,
        hostNetworkMapper: HostNetworkMapper
    ): HostRepository {
        return HostRepository_Impl(
            hostPingService = hostPingService,
            hostNetworkMapper = hostNetworkMapper
        )
    }
}