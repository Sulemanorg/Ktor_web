package com.xust.plugins.route

import com.xust.plugins.UserSession
import com.xust.plugins.service.getEmployeeByIdService
import io.ktor.application.*
import io.ktor.request.*
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
        index()
        login()
        logout()
        home()
        getEmployeeById()
    }
}

fun Route.index() {
    get("/") {
        call.respond(ThymeleafContent("login", model = mapOf("" to "")))
    }
}

fun Route.login() {
    post("/login") {
        val formParameters = call.receiveParameters()
        val username = formParameters["username"].toString()
        val password = formParameters["password"].toString()
        if (username.isNotEmpty() && password == "123456") {
            call.sessions.set("user_session", UserSession(username, password))
            call.respondRedirect("/home", permanent = true)
        } else {
            call.respond(ThymeleafContent("login", model = mapOf("msg" to "账号或密码错误!")))
        }
    }
}

fun Route.logout() {
    get("/logout") {
        call.sessions.clear<UserSession>()
        call.respondRedirect("/")
    }
}

fun Route.home() {
    get("/home") {
        val userSession = call.sessions.get("user_session")!! as UserSession
        call.respond(ThymeleafContent("home", model = mapOf("username" to userSession.username)))
    }
}

fun Route.getEmployeeById() {
    get("/employee/{id}") {
        val id = call.parameters["id"]
        val employeeList = getEmployeeByIdService(id!!.toInt())
        call.respondText(employeeList[0].toString())
    }
}



