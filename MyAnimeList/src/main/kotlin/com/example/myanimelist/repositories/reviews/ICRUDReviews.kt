package com.example.myanimelist.repositories.reviews

interface ICRUDReviews<T,ID> {
    /**
     * Añadir reviews al repositorio
     * @param review review a añadir
     * @return la review añadida
     */
    fun addReview(review: T): T?

    /**
     * Mostrar todas las reviews de un anime
     * @param animeId id del anime a ver todas sus reviews
     * @return lista con todas las reviews de ese anime
     */
    fun findByAnimeId(animeId: ID): List<T>

    /**
     * Añade puntación al anime
     * @param review review a añadir
     * @return review si ha sido añadida
     */
    fun addScore(review: T): T?
}