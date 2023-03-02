package gov.et.ets.di

import android.content.Context
import gov.et.ets.feature_users.network.HostPingService
import gov.et.ets.feature_users.network.model.HostNetworkMapper
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton
import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideHostNetworkMapper(): HostNetworkMapper {
        return HostNetworkMapper()
    }

    @Singleton
    @Provides
    fun provideHttpClient(context: Context): OkHttpClient {
        val httpTimeoutInSeconds = 30L

        return OkHttpClient.Builder()
            .connectTimeout(httpTimeoutInSeconds, TimeUnit.SECONDS)
            .readTimeout(httpTimeoutInSeconds, TimeUnit.SECONDS)
            .writeTimeout(httpTimeoutInSeconds, TimeUnit.SECONDS)
            .retryOnConnectionFailure(false)
            .build()
    }

    @Singleton
    @Provides
    fun provideHostPingService(httpClient: OkHttpClient): HostPingService {
        return Retrofit.Builder()
            .baseUrl("http://10.1.11.38:1994")
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .client(httpClient)
            .build()
            .create(HostPingService::class.java)
    }

}