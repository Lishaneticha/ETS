package gov.et.ets.di

import android.app.Application
import androidx.room.Room
import gov.et.ets.feature_users.data.repository.UserRepositoryImpl
import gov.et.ets.feature_users.data.source.local.UserDatabase
import gov.et.ets.feature_users.domain.repository.UserRepository
import gov.et.ets.utils.DATABASE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideUserDatabase(app: Application) = Room.databaseBuilder(
        app,
        UserDatabase::class.java,
        DATABASE_NAME
    ).fallbackToDestructiveMigration().build()

    @Provides
    @Singleton
    fun provideRepository(db: UserDatabase): UserRepository {
        return UserRepositoryImpl(db.userDao)
    }
}