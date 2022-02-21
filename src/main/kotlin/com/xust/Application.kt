package com.xust

import io.ktor.server.engine.*
import io.ktor.server.netty.*
import com.xust.plugins.*
import io.ktor.application.*
import io.ktor.network.tls.certificates.*
import org.slf4j.LoggerFactory
import java.io.File

fun main() {

    /*embeddedServer(Netty, port = 8080, host = "127.0.0.1") {
        configuration()
    }.start(wait = true)*/

    val keyStoreFile = File("build/keystore.jks")
    val keystore = generateCertificate(
        file = keyStoreFile,
        keyAlias = "sampleAlias",
        keyPassword = "foobar",
        jksPassword = "foobar"
    )

    val environment = applicationEngineEnvironment {
        log = LoggerFactory.getLogger("ktor.application")
        connector {
            host = "127.0.0.1"
            port = 8080
        }
        sslConnector(
            keyStore = keystore,
            keyAlias = "sampleAlias",
            keyStorePassword = { "foobar".toCharArray() },
            privateKeyPassword = { "foobar".toCharArray() }) {
            host = "127.0.0.1"
            port = 8443
            keyStorePath = keyStoreFile
        }
        module(Application::configuration)
    }

    embeddedServer(Netty, environment).start(wait = true)
}
