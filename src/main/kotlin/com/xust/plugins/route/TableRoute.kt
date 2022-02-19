package com.xust.plugins.route

import com.xust.plugins.entity.Employee
import com.xust.plugins.service.getAllEmployeesService
import io.ktor.application.*
import io.ktor.auth.*
import io.ktor.response.*
import io.ktor.routing.*
import io.ktor.thymeleaf.*

/**
 *  Table
 *
 * @author Liang on 2022/1/14
 */

fun Application.tableRoute() {
    routing {
        authenticate("auth-session") {

            get("/basic_table") {
                call.respond(ThymeleafContent("table/basic_table", model = mapOf("" to "")))
            }

            get("/editable_table") {
                call.respond(ThymeleafContent("table/editable_table", model = mapOf("" to "")))
            }

            get("/responsive_table") {
                call.respond(ThymeleafContent("table/responsive_table", model = mapOf("" to "")))
            }

            get("/dynamic_table") {
                val employees = getAllEmployeesService()
                call.respond(ThymeleafContent("table/dynamic_table", model = mapOf("employees" to employees)))
            }

        }
    }
}
