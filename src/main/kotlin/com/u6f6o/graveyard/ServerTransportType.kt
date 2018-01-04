package com.u6f6o.graveyard

import io.netty.channel.EventLoopGroup
import io.netty.channel.ServerChannel
import io.netty.channel.epoll.Epoll
import io.netty.channel.epoll.EpollEventLoopGroup
import io.netty.channel.epoll.EpollServerSocketChannel
import io.netty.channel.kqueue.KQueue
import io.netty.channel.kqueue.KQueueEventLoopGroup
import io.netty.channel.kqueue.KQueueServerSocketChannel
import io.netty.channel.nio.NioEventLoopGroup
import io.netty.channel.socket.nio.NioServerSocketChannel

class ServerTransportType private constructor(
        val bossgroup: EventLoopGroup,
        val workerGroup: EventLoopGroup,
        val channelClazz: Class<out ServerChannel>) {

    companion object {
        fun initialize(workerThreads: Int) : ServerTransportType {
            return when {
                Epoll.isAvailable() -> withEpoll(workerThreads)
                KQueue.isAvailable() -> withKQueue(workerThreads)
                else -> withNIO(workerThreads)
            }
        }

        private fun withEpoll(workerThreads: Int) =
                ServerTransportType(
                        EpollEventLoopGroup(1),
                        EpollEventLoopGroup(workerThreads),
                        EpollServerSocketChannel::class.java
                )

        private fun withKQueue(workerThreads: Int) =
                ServerTransportType(
                        KQueueEventLoopGroup(1),
                        KQueueEventLoopGroup(workerThreads),
                        KQueueServerSocketChannel::class.java
                )

        private fun withNIO(workerThreads: Int) =
                ServerTransportType(
                        NioEventLoopGroup(1),
                        NioEventLoopGroup(workerThreads),
                        NioServerSocketChannel::class.java
                )
    }
}
