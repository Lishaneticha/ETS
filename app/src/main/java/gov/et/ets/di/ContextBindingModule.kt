package gov.et.ets.di

import android.app.Application
import android.content.Context
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import gov.et.ets.UserApp

@Module
@InstallIn(SingletonComponent::class)
abstract class ContextBindingModule {

    @Binds
    abstract fun context(userApp: UserApp): Context
}