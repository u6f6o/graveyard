package com.u6f6o.graveyard

import com.u6f6o.graveyard.server.ServerEndpoint

fun main(args: Array<String>) {
    val endpoint = ServerEndpoint(8080, 10)
    endpoint.run()
}