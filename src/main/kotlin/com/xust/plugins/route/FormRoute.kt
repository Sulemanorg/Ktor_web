package com.xust.plugins.route

import io.ktor.application.*
import io.ktor.auth.*
import io.ktor.http.content.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import io.ktor.thymeleaf.*
import java.io.File

/**
 *  Form
 *
 * @author Liang on 2022/1/14
 */
fun Application.formRoute() {

    routing {

        authenticate("auth-session") {

            get("/form_layouts") {
                call.respond(ThymeleafContent("form/form_layouts", model = mapOf("" to "")))
            }

            post("/upload") {
                var fileDescription = ""
                var fileName = ""
                val multipartData = call.receiveMultipart()
                multipartData.forEachPart { part ->
                    when (part) {
                        is PartData.FormItem -> {
                            fileDescription += part.value
                        }
                        is PartData.FileItem -> {
                            fileName += part.originalFileName as String
                            var fileBytes = part.streamProvider().readBytes()
                            File("E:\\uploadtest\\$fileName").writeBytes(fileBytes)
                        }
                    }
                }
                call.respondText("$fileDescription is uploaded to 'uploads/$fileName'")
            }
        }
    }
}


