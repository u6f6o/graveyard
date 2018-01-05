package com.u6f6o.graveyard

fun main(args: Array<String>) {
    val endpoint = ServerEndpoint(8080, 10)
    endpoint.run()
}