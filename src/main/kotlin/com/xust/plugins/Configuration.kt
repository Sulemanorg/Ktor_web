package com.xust.plugins

import io.ktor.application.*
import io.ktor.auth.*
import io.ktor.http.content.*
import io.ktor.response.*
import io.ktor.routing.*
import io.ktor.sessions.*
import io.ktor.thymeleaf.*
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver
import java.io.File

/**
 * 全局配置
 *
 * @author Liang on 2022/1/12
 */
fun Application.configuration() {

    install(Thymeleaf) {
        setTemplateResolver(ClassLoaderTemplateResolver().apply {
            prefix = "templates/"
            suffix = ".html"
            characterEncoding = "utf-8"
        })
    }

    install(Sessions) {
        cookie<UserSession>("user_session", directorySessionStorage(File(".session"))) {
            cookie.path = "/"
            cookie.maxAgeInSeconds = 60
        }
    }

    install(Authentication) {

        form("auth_form") {
            userParamName = "username"
            passwordParamName = "password"
            validate { credentials ->
                if (credentials.name.isNotEmpty() && credentials.password == "123456" ) {
                    UserIdPrincipal(credentials.name)
                } else {
                    null
                }
            }
            challenge {
                call.respond(ThymeleafContent("login", model = mapOf("msg" to "账号或密码错误!")))
            }
        }

        session<UserSession>("auth_session") {
            validate { session ->
                if(session.username.isNotEmpty()) {
                    session
                } else {
                    null
                }
            }
            challenge {
                call.respondRedirect("/login")
            }
        }
    }

    routing {
        static {
            resources("static")
        }
    }

}

data class UserSession(val username: String, val password: String) : Principal


