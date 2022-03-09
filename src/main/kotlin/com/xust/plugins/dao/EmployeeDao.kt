package com.xust.plugins.dao

import com.xust.plugins.dao.table.TbEmployee
import com.xust.plugins.dao.init.DATABASE
import com.xust.plugins.entity.Employee
import io.ktor.routing.*
import org.ktorm.dsl.*

/**
 * EmployeeDao
 *
 * @author Liang on 2022/1/17
 */

var getEmployeeByIdDao : (Int) -> List<Employee> = { listOf() }

var getAllEmployeesDao : () -> List<Employee> = { listOf() }
