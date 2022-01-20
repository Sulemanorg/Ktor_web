package com.xust.plugins.dao

import com.xust.plugins.dao.table.TbEmployee
import com.xust.plugins.dao.init.DATABASE
import com.xust.plugins.entity.Employee
import org.ktorm.dsl.*

/**
 *
 *
 * @author Liang on 2022/1/17
 */

fun getEmployeeByIdDao(id: Long): List<Employee>{
    return DATABASE.from(TbEmployee).select()
        .where { TbEmployee.id eq id.toInt() }
        .map { row -> TbEmployee.createEntity(row) }
}

fun getAllEmployeesDao(): List<Employee> {
    return DATABASE.from(TbEmployee).select().map { row -> TbEmployee.createEntity(row) }
}