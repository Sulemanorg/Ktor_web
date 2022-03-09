package com.xust.plugins

import com.fasterxml.jackson.core.util.DefaultIndenter
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter
import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.xust.plugins.dao.impl.employeeDaoImpl
import com.xust.plugins.route.formRoute
import com.xust.plugins.route.indexRoute
import com.xust.plugins.route.tableRoute
import com.xust.plugins.service.impl.employeeServiceImpl
import io.ktor.application.*
import io.ktor.auth.*
import io.ktor.features.*
import io.ktor.http.content.*
import io.ktor.jackson.*
import io.ktor.response.*
import io.ktor.routing.*
import io.ktor.sessions.*
import io.ktor.thymeleaf.*
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver

/**
 * Global configuration
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
                    println("Authenticate successfully!")
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

    install(DefaultHeaders)
    install(Compression)
    install(CallLogging)
    install(ContentNegotiation) {
        jackson {
            configure(SerializationFeature.INDENT_OUTPUT, true)
            setDefaultPrettyPrinter(DefaultPrettyPrinter().apply {
                indentArraysWith(DefaultPrettyPrinter.FixedSpaceIndenter.instance)
                indentObjectsWith(DefaultIndenter("  ", "\n"))
            })
            registerModule(JavaTimeModule())  // support java.time.* types
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

    // init dao
    routing {
        employeeDaoImpl()
    }

    // init service
    routing {
        employeeServiceImpl()
    }
}

data class UserSession(val name: String, val count: Int) : Principal




