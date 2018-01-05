package com.u6f6o.graveyard

import io.netty.channel.ChannelHandlerContext
import io.netty.channel.ChannelInboundHandlerAdapter
import io.netty.handler.codec.http.*

// TODO("error handling")
class RequestDispatcher : ChannelInboundHandlerAdapter() {

    object Domain {
        const val Movies = "/graveyard/movies/"
        const val Series = "/graveyard/series/"
        const val Actors = "/graveyard/actors/"
    }

    override fun channelRead(ctx: ChannelHandlerContext?, msg: Any?) {
        if (msg is FullHttpRequest) {
            val decoder = QueryStringDecoder(msg.uri())
            val path = decoder.path()

            when {
                path.startsWith(Domain.Movies) -> dispatchMovieQuery(ctx,  decoder)
                path.startsWith(Domain.Series) -> dispatchSeriesQuery(ctx, decoder)
                path.startsWith(Domain.Actors) -> dispatchActorQuery(ctx, decoder)
                else -> answerWithFailure(ctx)
            }
        } else {
            super.channelRead(ctx, msg)
        }
    }

    private fun dispatchMovieQuery(ctx: ChannelHandlerContext?, decoder: QueryStringDecoder) {
        val id = extractId(decoder, Domain.Movies, '/') ?: -1
        super.channelRead(ctx, MovieQuery(id))
    }

    private fun dispatchSeriesQuery(ctx: ChannelHandlerContext?, decoder: QueryStringDecoder) {
        val id = extractId(decoder, Domain.Series, '/') ?: -1
        super.channelRead(ctx, SeriesQuery(id))
    }

    private fun dispatchActorQuery(ctx: ChannelHandlerContext?, decoder: QueryStringDecoder) {
        val id = extractId(decoder, Domain.Actors, '/') ?: -1
        super.channelRead(ctx, ActorQuery(id))
    }

    private fun answerWithFailure(ctx: ChannelHandlerContext?) {
        ctx?.writeAndFlush(DefaultHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.BAD_REQUEST))
    }

    private fun extractId(decoder: QueryStringDecoder, prefix: String, suffix: Char) : Int? {
        val path = decoder.path()
        return path.substringAfter(prefix).substringBefore(suffix).toIntOrNull()
    }
}
