package com.u6f6o.graveyard

import io.netty.channel.EventLoopGroup
import io.netty.channel.epoll.Epoll
import io.netty.channel.epoll.EpollEventLoopGroup
import io.netty.channel.kqueue.KQueue
import io.netty.channel.kqueue.KQueueEventLoopGroup
import io.netty.channel.nio.NioEventLoopGroup

class TransportTypeHolder private constructor(val bossgroup: EventLoopGroup, val workerGroup: EventLoopGroup) {
    companion object {
        fun create(workerThreads: Int) : TransportTypeHolder {
            return when {
                Epoll.isAvailable() -> {
                    TransportTypeHolder(EpollEventLoopGroup(1), EpollEventLoopGroup(workerThreads))
                }
                KQueue.isAvailable() -> {
                    TransportTypeHolder(KQueueEventLoopGroup(1), KQueueEventLoopGroup(workerThreads))
                }
                else -> {
                    TransportTypeHolder(NioEventLoopGroup(1), NioEventLoopGroup(workerThreads))
                }
            }
        }

    }
}
