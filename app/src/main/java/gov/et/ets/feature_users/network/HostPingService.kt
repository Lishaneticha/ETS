package gov.et.ets.feature_users.network

import gov.et.ets.feature_users.network.response.HostPingResult
import retrofit2.http.Query
import retrofit2.http.POST

interface HostPingService {
    @POST("/ping")
    suspend fun postPing(
        @Query("urls") urls: List<String>
    ): HostPingResult
}