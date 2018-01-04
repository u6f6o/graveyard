package com.u6f6o.graveyard

import io.netty.channel.EventLoopGroup
import io.netty.channel.epoll.Epoll
import io.netty.channel.epoll.EpollEventLoopGroup
import io.netty.channel.epoll.EpollServerSocketChannel
import io.netty.channel.kqueue.KQueue
import io.netty.channel.kqueue.KQueueEventLoopGroup
import io.netty.channel.kqueue.KQueueServerSocketChannel
import io.netty.channel.nio.NioEventLoopGroup
import io.netty.channel.socket.nio.NioServerSocketChannel

class ServerTransportType private constructor(val bossgroup: EventLoopGroup, val workerGroup: EventLoopGroup, val channelClazz: Class<*>) {
    companion object {
        fun create(workerThreads: Int) : ServerTransportType {
            return when {
                Epoll.isAvailable() -> withEpoll(workerThreads)
                KQueue.isAvailable() -> withKQueue(workerThreads)
                else -> withNIO(workerThreads)
            }
        }

        private fun withEpoll(workerThreads: Int) =
                ServerTransportType(
                        bossgroup = EpollEventLoopGroup(1),
                        workerGroup = EpollEventLoopGroup(workerThreads),
                        channelClazz = EpollServerSocketChannel::class.java
                )

        private fun withKQueue(workerThreads: Int) =
                ServerTransportType(
                        bossgroup = KQueueEventLoopGroup(1),
                        workerGroup = KQueueEventLoopGroup(workerThreads),
                        channelClazz = KQueueServerSocketChannel::class.java
                )

        private fun withNIO(workerThreads: Int) =
                ServerTransportType(
                        bossgroup = NioEventLoopGroup(1),
                        workerGroup = NioEventLoopGroup(workerThreads),
                        channelClazz = NioServerSocketChannel::class.java
                )
    }
}
