package com.u6f6o.graveyard

import io.netty.channel.ChannelHandlerContext
import io.netty.channel.SimpleChannelInboundHandler

class SeriesQueryHandler : SimpleChannelInboundHandler<SeriesQuery>() {
    override fun channelRead0(ctx: ChannelHandlerContext?, msg: SeriesQuery?) {
        println("Series query id " + msg?.id)
    }
}