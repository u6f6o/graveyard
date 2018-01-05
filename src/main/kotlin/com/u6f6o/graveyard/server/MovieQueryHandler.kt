package com.u6f6o.graveyard.server

import com.u6f6o.graveyard.MovieQuery
import io.netty.channel.ChannelHandlerContext
import io.netty.channel.SimpleChannelInboundHandler

class MovieQueryHandler : SimpleChannelInboundHandler<MovieQuery>() {

    override fun channelRead0(ctx: ChannelHandlerContext?, msg: MovieQuery?) {
        println("Movie query id " + msg?.id)
    }

}