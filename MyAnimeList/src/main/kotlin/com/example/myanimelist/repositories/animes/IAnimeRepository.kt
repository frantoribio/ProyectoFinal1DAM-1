package com.example.myanimelist.repositories.animes

import com.example.myanimelist.models.Anime
import java.util.*

interface IAnimeRepository : ICRUDRepository<UUID, Anime> {

    fun findByTitle(title: String): Anime?

}