package com.u6f6o.graveyard

import io.netty.bootstrap.ServerBootstrap
import io.netty.buffer.ByteBufAllocator
import io.netty.channel.ChannelFuture
import io.netty.channel.ChannelOption
import java.net.InetSocketAddress

class ServerEndpoint(
        private val port: Int,
        private val workerThreads: Int,
        private var cf: ChannelFuture? = null) {

    @Throws(Exception::class)
    fun run() {
        val transport = ServerTransportType.initialize(workerThreads)
        val bootstrap = ServerBootstrap()

        bootstrap.group(transport.bossgroup, transport.workerGroup)
                .channel(transport.channelClazz)
                .childOption(ChannelOption.ALLOCATOR, ByteBufAllocator.DEFAULT)
                .option(ChannelOption.ALLOCATOR, ByteBufAllocator.DEFAULT)
                .childHandler(ServerPipelineInitializer())

        this.cf = bootstrap.bind(InetSocketAddress(port)).sync()
    }
}