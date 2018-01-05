package com.u6f6o.graveyard

import io.netty.channel.ChannelHandlerContext
import io.netty.channel.SimpleChannelInboundHandler

class MovieQueryHandler : SimpleChannelInboundHandler<MovieQuery>() {
    override fun channelRead0(ctx: ChannelHandlerContext?, msg: MovieQuery?) {
        println("Movie query id " + msg?.id)
    }

}