package com.u6f6o.graveyard

import io.netty.channel.ChannelHandlerContext
import io.netty.channel.ChannelInboundHandlerAdapter
import io.netty.handler.codec.http.FullHttpRequest
import io.netty.handler.codec.http.QueryStringDecoder

// TODO("error handling")
class RequestDispatcher : ChannelInboundHandlerAdapter() {

    override fun channelRead(ctx: ChannelHandlerContext?, msg: Any?) {
        if (msg is FullHttpRequest) {
            val decoder = QueryStringDecoder(msg.uri())
            val path = decoder.path()

            when {
                path.startsWith("/graveyard/movies/") -> dispatchMovieQuery(ctx,  decoder)
                path.startsWith("/graveyard/series/") -> dispatchSeriesQuery(ctx, decoder)
                path.startsWith("/graveyard/actors/") -> dispatchActorQuery(ctx, decoder)
            }
        } else {
            super.channelRead(ctx, msg)
        }
    }

    private fun dispatchMovieQuery(ctx: ChannelHandlerContext?, decoder: QueryStringDecoder) {
        val id = extractId(decoder, "/graveyard/movies/", '/') ?: -1
        super.channelRead(ctx, MovieQuery(id))
    }

    private fun dispatchSeriesQuery(ctx: ChannelHandlerContext?, decoder: QueryStringDecoder) {
        val id = extractId(decoder, "/graveyard/series/", '/') ?: -1
        super.channelRead(ctx, SeriesQuery(id))
    }

    private fun dispatchActorQuery(ctx: ChannelHandlerContext?, decoder: QueryStringDecoder) {
        val id = extractId(decoder, "/graveyard/actors/", '/') ?: -1
        super.channelRead(ctx, ActorQuery(id))
    }

    private fun extractId(decoder: QueryStringDecoder, prefix: String, suffix: Char) : Int? {
        val path = decoder.path()
        return path.substringAfter(prefix).substringBefore(suffix).toIntOrNull()
    }
}
