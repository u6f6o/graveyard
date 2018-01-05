package com.u6f6o.graveyard.server

import io.netty.channel.ChannelInitializer
import io.netty.channel.socket.SocketChannel
import io.netty.handler.codec.http.HttpObjectAggregator
import io.netty.handler.codec.http.HttpContentCompressor
import io.netty.handler.codec.http.HttpServerCodec



class ServerPipelineInitializer : ChannelInitializer<SocketChannel>() {
    override fun initChannel(channel: SocketChannel) {
        val pipeline = channel.pipeline()
        pipeline.addLast(HttpServerCodec())
        pipeline.addLast(HttpContentCompressor())
        pipeline.addLast(HttpObjectAggregator(1048576))
        pipeline.addLast("jsonEncoder", JsonEncoder())
        pipeline.addLast("dispatcher", RequestDispatcher())
        pipeline.addLast("movieHandler", MovieQueryHandler())
        pipeline.addLast("actorHandler", ActorQueryHandler())
    }
}