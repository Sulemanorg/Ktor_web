package com.xust.plugins.dao.impl

import com.xust.plugins.dao.getAllEmployeesDao
import com.xust.plugins.dao.getEmployeeByIdDao
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

fun Routing.employeeDaoImpl() {

    getEmployeeByIdDao = fun(id: Int): List<Employee>{
        return DATABASE.from(TbEmployee).select()
            .where { TbEmployee.id eq id }
            .map { row -> TbEmployee.createEntity(row) }
    }

    getAllEmployeesDao = fun(): List<Employee> {
        return DATABASE.from(TbEmployee).select().map { row -> TbEmployee.createEntity(row) }
    }

}