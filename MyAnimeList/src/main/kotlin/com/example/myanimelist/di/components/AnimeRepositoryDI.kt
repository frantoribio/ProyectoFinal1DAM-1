package com.example.myanimelist.di.components

import com.example.myanimelist.controllers.AnimeController
import com.example.myanimelist.di.modules.DataBaseManagerModule
import com.example.myanimelist.repositories.animes.AnimeRepository
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [DataBaseManagerModule::class])
interface AnimeRepositoryDI {
    fun build() : AnimeRepository
    fun inject(controller : AnimeController)
}