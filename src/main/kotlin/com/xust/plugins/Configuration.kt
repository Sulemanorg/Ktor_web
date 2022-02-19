package com.xust.plugins

import com.xust.plugins.route.formRoute
import com.xust.plugins.route.indexRoute
import com.xust.plugins.route.tableRoute
import com.xust.plugins.service.getAllEmployeesService
import io.ktor.application.*
import io.ktor.auth.*
import io.ktor.http.content.*
import io.ktor.response.*
import io.ktor.routing.*
import io.ktor.sessions.*
import io.ktor.thymeleaf.*
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver

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
        cookie<UserSession>("user_session") {
            cookie.path = "/"
            cookie.maxAgeInSeconds = 60
        }
    }

    install(Authentication) {
        form("auth-form") {
            userParamName = "username"
            passwordParamName = "password"
            validate { credentials ->
                if (credentials.name == "admin" && credentials.password == "123456") {
                    UserIdPrincipal(credentials.name)
                } else {
                    null
                }
            }
            challenge {
                call.respond(ThymeleafContent("login", model = mapOf("msg" to "账号或密码错误!")))
            }
        }

        session<UserSession>("auth-session") {
            validate { session ->
                if(session.name =="admin") {
                    println("Store session successfully!")
                    session
                } else {
                    null
                }
            }
            challenge {
                call.respond(ThymeleafContent("login", model = mapOf("msg" to "请先登录!")))
            }
        }
    }

    // static resources
    routing {
        static {
            resources("static")
        }
    }

    // register route
    routing {
        indexRoute()
        formRoute()
        tableRoute()
    }

}

data class UserSession(val name: String, val count: Int) : Principal




