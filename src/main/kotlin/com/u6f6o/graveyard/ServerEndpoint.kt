package com.u6f6o.graveyard

import io.netty.channel.nio.NioEventLoopGroup

class ServerEndpoint(port: Int) {
    val port: Int = port

    fun run() {
        val bossGroup = NioEventLoopGroup()
        val workerGroup = NioEventLoopGroup();


    }
}