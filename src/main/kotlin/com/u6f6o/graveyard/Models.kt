package com.u6f6o.graveyard

import java.time.Year

data class MovieQuery(val id: Int)
data class ActorQuery(val id: Int)

data class Actor(val id: Int, val name: String)
data class Movie(val id: Int, val title: String, var description: String?, var releaseYear: Year?, var actors: List<Actor>?)
