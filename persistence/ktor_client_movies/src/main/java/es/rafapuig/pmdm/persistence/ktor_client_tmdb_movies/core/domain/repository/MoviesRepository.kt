package es.rafapuig.pmdm.persistence.ktor_client_tmdb_movies.core.domain.repository

import es.rafapuig.pmdm.persistence.ktor_client_tmdb_movies.core.domain.model.Movie

/**
 * La interface del repositorio definida en la capa de dominio
 * tiene como objetivo abstraer la implementación de la capa de datos.
 * Logra que sea la capa de datos la que dependa de la capa de dominio:
 * La clase en la capa de datos "conoce" la interface que implementa
 * Por tanto: Data -depende de-> Dominio
 * Y con esto se consigue la INVERSION DE DEPENDENCIAS
 * (que data --> domain y no domain --> data)
 */
interface MoviesRepository {

    /**
     * Las operaciones del repositorio las definimos como suspend functions
     * ya que en este caso, aunque son funciones que consultan y recuperan
     * información de la capa de datos, son one-shot, es decir, devuelven
     * un único resultado y no un flujo de datos (FLOW).
     */

    /**
     * Los métodos del repositorio que devuelven información
     * lo hacen en forma de lista : List<Movie>
     * Y los elementos de la lista deben ser instancias del modelo de datos de dominio.
     * En este caso la data class Movie
     */

    /** Recupera las películas ordenados por popularidad */
    suspend fun fetchPopularMovies(page: Int = 1): List<Movie>

    /** Recupera las pelilcuas ordenados por valoración */
    suspend fun fetchTopRatedMovies(page: Int = 1): List<Movie>

    /** Recuepera peliculas que coincidan con el texto de búsqueda */
    suspend fun searchMovies(query: String, page: Int = 1): List<Movie>
}