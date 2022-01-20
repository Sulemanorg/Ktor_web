package com.xust.plugins

import com.xust.plugins.route.formRoute
import com.xust.plugins.route.indexRoute
import com.xust.plugins.route.tableRoute
import io.ktor.application.*

/**
 * 注册路由
 *
 * @author Liang on 2022/1/12
 */
fun Application.configureRouting() {
    indexRoute()
    tableRoute()
    formRoute()
}
