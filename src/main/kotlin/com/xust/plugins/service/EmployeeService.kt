package com.xust.plugins.service

import com.xust.plugins.dao.getAllEmployeesDao
import com.xust.plugins.dao.getEmployeeByIdDao
import com.xust.plugins.entity.Employee

/**
 *
 *
 * @author Liang on 2022/1/18
 */

fun getEmployeeByIdService(id: Int): List<Employee> {
    return getEmployeeByIdDao(id)
}

fun getAllEmployeesService(): List<Employee> {
    return getAllEmployeesDao()
}