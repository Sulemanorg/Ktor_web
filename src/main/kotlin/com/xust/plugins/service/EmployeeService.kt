package com.xust.plugins.service

import com.xust.plugins.dao.getAllEmployeesDao
import com.xust.plugins.dao.getEmployeeByIdDao
import com.xust.plugins.entity.Employee
import io.ktor.routing.*

/**
 * Employee Service
 *
 * @author Liang on 2022/1/18
 */

var getEmployeeByIdService : (Int) -> List<Employee> = { listOf() }

var getAllEmployeesService : () -> List<Employee> = { listOf() }

