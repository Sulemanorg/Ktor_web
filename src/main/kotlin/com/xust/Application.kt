package com.xust

import io.ktor.server.engine.*
import io.ktor.server.netty.*
import com.xust.plugins.*

fun main() {
    embeddedServer(Netty, port = 8080, host = "127.0.0.1") {
        configureRouting()
        configuration()
    }.start(wait = true)
}
