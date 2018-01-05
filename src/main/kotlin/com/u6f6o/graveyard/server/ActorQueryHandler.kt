package com.u6f6o.graveyard.server

import com.u6f6o.graveyard.ActorQuery
import io.netty.channel.ChannelHandlerContext
import io.netty.channel.SimpleChannelInboundHandler

class ActorQueryHandler : SimpleChannelInboundHandler<ActorQuery>() {
    override fun channelRead0(ctx: ChannelHandlerContext?, msg: ActorQuery?) {
        println("Actor query id " + msg?.id)
    }
}