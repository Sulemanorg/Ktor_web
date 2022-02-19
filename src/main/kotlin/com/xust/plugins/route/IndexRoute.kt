package com.xust.plugins.route

import com.xust.plugins.UserSession
import com.xust.plugins.service.getEmployeeByIdService
import io.ktor.application.*
import io.ktor.auth.*
import io.ktor.response.*
import io.ktor.routing.*
import io.ktor.sessions.*
import io.ktor.thymeleaf.*

/**
 *  Index
 *
 * @author Liang on 2022/1/13
 */
fun Application.indexRoute() {

    routing {

        get("/") {
            call.respond(ThymeleafContent("login", model = mapOf("" to "")))
        }

        authenticate("auth-form") {
            post("/login") {
                val userName = call.principal<UserIdPrincipal>()?.name.toString()
                call.sessions.set(UserSession(name = userName, count = 1))
                call.respondRedirect("/home")
            }
        }

        authenticate("auth-session") {

            get("/logout") {
                call.sessions.clear<UserSession>()
                call.respondRedirect("/")
            }

            get("/home") {
                val userSession = call.sessions.get("user_session")!! as UserSession
                call.respond(ThymeleafContent("home", model = mapOf("username" to userSession.name)))
            }

            get("/employee/{id}") {
                val id = call.parameters["id"]
                val employeeList = getEmployeeByIdService(id!!.toInt())
                call.respondText(employeeList[0].toString())
            }

        }
    }

}



