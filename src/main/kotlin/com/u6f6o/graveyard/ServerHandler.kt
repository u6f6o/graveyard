package com.u6f6o.graveyard

import io.netty.channel.ChannelHandlerContext
import io.netty.channel.ChannelInboundHandlerAdapter
import io.netty.handler.codec.http.FullHttpRequest
import io.netty.util.CharsetUtil

class ServerHandler : ChannelInboundHandlerAdapter() {
    override fun channelRead(ctx: ChannelHandlerContext?, msg: Any?) {
        when(msg) {
            is FullHttpRequest -> dispatch(msg)
            else -> super.channelRead(ctx, msg)
        }
    }

    private fun dispatch(msg: FullHttpRequest) {
        println(msg.content().toString(CharsetUtil.UTF_8))
    }
}