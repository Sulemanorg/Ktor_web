package com.xust.plugins.service.impl

import com.xust.plugins.dao.getAllEmployeesDao
import com.xust.plugins.dao.getEmployeeByIdDao
import com.xust.plugins.entity.Employee
import com.xust.plugins.service.getAllEmployeesService
import com.xust.plugins.service.getEmployeeByIdService
import io.ktor.routing.*

/**
 * Employee Service Impl
 *
 * @author Liang on 2022/1/18
 */

fun Routing.employeeServiceImpl() {

    getEmployeeByIdService = fun(id: Int): List<Employee> {
        return getEmployeeByIdDao(id)
    }

    getAllEmployeesService = fun(): List<Employee> {
        return getAllEmployeesDao()
    }
}
