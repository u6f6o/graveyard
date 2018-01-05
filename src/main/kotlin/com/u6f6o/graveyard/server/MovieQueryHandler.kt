package com.u6f6o.graveyard.server

import com.u6f6o.graveyard.Movie
import com.u6f6o.graveyard.MovieQuery
import io.netty.channel.ChannelHandlerContext
import io.netty.channel.SimpleChannelInboundHandler

class MovieQueryHandler : SimpleChannelInboundHandler<MovieQuery>() {

    override fun channelRead0(ctx: ChannelHandlerContext?, msg: MovieQuery?) {
        ctx?.writeAndFlush(Movie(123, "Departed"))
    }

}