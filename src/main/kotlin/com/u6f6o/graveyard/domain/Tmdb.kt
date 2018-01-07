package com.u6f6o.graveyard.domain

interface TmdbModel

data class TmdbMovie(
        val id: Int,
        val adult: Boolean,
        val backdropPath: String?,
        val budget: Int,
        val genres: List<TmdbGenre>,
        val homepage: String?,
        val originalLanguage: String,
        val originalTitle: String,
        val overview: String?,
        val popularity: Double,
        val posterPath: String?,
        val productionCompanies: List<TmdbCompany>,
        val productionCountries: List<TmdbCountry>,
        val releaseDate: String,
        val revenue: Int,
        val runtime: Int?,
        val spokenLanguages: List<TmdbLanguage>,
        val status: String,
        val tagline: String?,
        val title: String,
        val video: Boolean,
        val voteAverage: Double,
        val voteCount: Int
) : TmdbModel {
    override fun equals(other: Any?): Boolean {
        if (other === this) return true
        if (other !is TmdbMovie) return false
        return id == other.id
    }
    override fun hashCode(): Int {
        return id.hashCode()
    }
}

data class TmdbGenre(
        val id: Int,
        val name: String
) : TmdbModel {
    override fun equals(other: Any?): Boolean {
        if (other === this) return true
        if (other !is TmdbGenre) return false
        return id == other.id
    }
    override fun hashCode(): Int {
        return id.hashCode()
    }
}

data class TmdbCompany(
        val id: Int,
        val description: String?,
        val headquarters: String,
        val homepage: String,
        val name: String
) : TmdbModel {
    override fun equals(other: Any?): Boolean {
        if (other === this) return true
        if (other !is TmdbCompany) return false
        return id == other.id
    }
    override fun hashCode(): Int {
        return id.hashCode()
    }
}

data class TmdbCountry(
        val isoCode: String,
        val name: String
) : TmdbModel {
    override fun equals(other: Any?): Boolean {
        if (other === this) return true
        if (other !is TmdbCountry) return false
        return isoCode == other.isoCode
    }
    override fun hashCode(): Int {
        return isoCode.hashCode()
    }
}

data class TmdbLanguage(
        val isoCode: String,
        val name: String
) : TmdbModel {
    override fun equals(other: Any?): Boolean {
        if (other === this) return true
        if (other !is TmdbLanguage) return false
        return isoCode == other.isoCode
    }
    override fun hashCode(): Int {
        return isoCode.hashCode()
    }
}