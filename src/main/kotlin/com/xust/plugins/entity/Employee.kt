package com.xust.plugins.entity

import java.time.LocalDate

/**
 *
 *
 * @author Liang on 2022/1/18
 */
data class Employee(
    val id : Int,
    var name : String,
    var job : String,
    var managerId : Int,
    var hireDate : LocalDate,
    var salary : Long,
    var departmentId : Int
)