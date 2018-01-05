package com.u6f6o.graveyard

import java.time.LocalDate
import java.time.Year

data class MovieQuery(val id: Int)
data class ActorQuery(val id: Int)

data class Movie(
        val id: Int,
        val title: String,
        var description: String?,
        var releaseDate: LocalDate?,
        var runtime: Int?,
        var cast: Cast?
)
data class Cast(
        val id: Int,
        val actors: List<Actor>?,
        val crew: List<CrewMember>?
)
data class Actor(
        val id: Int,
        val realName: String,
        val characterName: String
)
data class CrewMember(
        val id: Int,
        val department: String,
        val job: String
)
data class Genre(
        val id: Int,
        val name: String
)

