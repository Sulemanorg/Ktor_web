package com.xust.plugins.dao

import com.xust.plugins.entity.Employee

/**
 * EmployeeDao
 *
 * @author Liang on 2022/1/17
 */

var getEmployeeByIdDao : (Int) -> List<Employee> = { listOf() }

var getAllEmployeesDao : () -> List<Employee> = { listOf() }
