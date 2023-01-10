package ru.flobsterable.flashCards.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.flobsterable.flashCards.navigation.AppNavigation
import ru.flobsterable.flashCards.navigation.AppNavigationImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface AppModule {

    @Singleton
    @Binds
    fun bindNavigation(
        appNavigationImpl: AppNavigationImpl
    ): AppNavigation
}
