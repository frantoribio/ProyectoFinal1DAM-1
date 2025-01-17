package com.example.myanimelist.repositories.animes

import com.example.myanimelist.extensions.execute
import com.example.myanimelist.extensions.getLogger
import com.example.myanimelist.managers.DataBaseManager
import com.example.myanimelist.models.Anime
import org.apache.logging.log4j.Logger
import org.koin.core.component.KoinComponent
import java.util.*


class AnimeRepository(
    private var databaseManager: DataBaseManager,
) : IAnimeRepository, KoinComponent {

    val logger: Logger = getLogger<AnimeRepository>()

    override fun findById(id: UUID): Anime? {
        val query = "SELECT * FROM animes WHERE id = ?"

        databaseManager.execute(logger) {
            val result = databaseManager.select(query, id.toString())

            if (!result.next()) return null

            val anime = Anime(
                id = UUID.fromString(result.getString("id")),
                title = result.getString("title"),
                titleEnglish = result.getString("title_english"),
                types = result.getString("type"),
                episodes = result.getInt("episodes"),
                status = result.getString("status"),
                date = result.getDate("releaseDate").toLocalDate(),
                rating = result.getString("rating"),
                genres = result.getString("genre").split(",").toList(),
                img = result.getString("imageUrl")
            )
            logger.info("Encontrado el anime $anime")
            return anime
        }
        return null
    }

    override fun findAll(): List<Anime> {
        val query = "SELECT * FROM animes"
        val animes = mutableListOf<Anime>()
        databaseManager.execute(logger) {
            val result = databaseManager.select(query)
            while (result.next()) {
                val anime = Anime(
                    id = UUID.fromString(result.getString("id")),
                    title = result.getString("title"),
                    titleEnglish = result.getString("title_english"),
                    types = result.getString("type"),
                    episodes = result.getInt("episodes"),
                    status = result.getString("status"),
                    date = result.getDate("releaseDate").toLocalDate(),
                    rating = result.getString("rating"),
                    genres = result.getString("genre").split(",").toList(),
                    img = result.getString("imageUrl")
                )
                animes.add(anime)
                logger.info("Encontrados los animes: $animes")
            }
            return animes
        }
        return emptyList()
    }

    override fun update(item: Anime): Anime? {
        val query = "UPDATE animes SET " +
                "title = ?," +
                "title_english = ?," +
                "status = ?," +
                "genre = ?," +
                "releaseDate = ?," +
                "imageUrl = ?," +
                "episodes = ?," +
                "rating = ?," +
                "type = ?" +
                "WHERE id = ?"

        databaseManager.execute(logger) {
            databaseManager.update(
                query,
                item.title,
                item.titleEnglish,
                item.status,
                item.genres.joinToString(separator = ", "),
                item.date,
                item.img,
                item.episodes,
                item.rating,
                item.types,
                item.id.toString()
            )
            logger.info("actualizado el anime $item")
            return item
        }

        return null
    }

    override fun add(item: Anime): Anime? {
        val query = "INSERT INTO animes VALUES( ?, ?, ?, ?, ?, ?, ?, ?, ?, ? )"
        databaseManager.execute(logger) {
            databaseManager.insert(
                query,
                item.id,
                item.title,
                item.titleEnglish,
                item.status,
                item.genres.joinToString(separator = ", "),
                item.date,
                item.img,
                item.episodes,
                item.rating,
                item.types
            )
            logger.info("Añadido anime $item")
            return item
        }
        return null
    }

    override fun delete(id: UUID) {
        val query = "DELETE FROM animes WHERE id = ?"
        databaseManager.execute(logger) {
            databaseManager.delete(query, id)
            logger.info("Eliminado el anime ${findById(id)}")
        }
    }
}
