package com.u6f6o.graveyard

import java.time.LocalDate

interface Response {}

data class MovieQuery(val id: Int)
data class ActorQuery(val id: Int)

data class Movie(
        val id: Int,
        val title: String,
        var description: String? = null,
        var releaseDate: LocalDate? = null,
        var runtime: Int? = null,
        var cast: Cast? = null
) : Response

data class Cast(
        val id: Int,
        val actors: List<Actor>?,
        val crew: List<CrewMember>?
) : Response

data class Actor(
        val id: Int,
        val realName: String,
        val characterName: String
) : Response

data class CrewMember(
        val id: Int,
        val department: String,
        val job: String
) : Response

data class Genre(
        val id: Int,
        val name: String
) : Response

