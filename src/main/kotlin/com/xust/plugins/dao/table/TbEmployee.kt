package com.xust.plugins.dao.table

import com.xust.plugins.entity.Employee
import org.ktorm.dsl.QueryRowSet
import org.ktorm.schema.*
import java.time.LocalDate

/**
 *
 *
 * @author Liang on 2022/1/18
 */
object TbEmployee : BaseTable<Employee>("t_employee") {

    val id = int("id").primaryKey()
    val name = varchar("name")
    val job = varchar("job")
    val managerId = int("manager_id")
    val hireDate = date("hire_date")
    val salary = long("salary")
    val departmentId = int("department_id")

    override fun doCreateEntity(row: QueryRowSet, withReferences: Boolean) = Employee(
        id = row[id] ?: 0,
        name = row[name].orEmpty(),
        job = row[job].orEmpty(),
        managerId = row[managerId] ?: 0,
        hireDate = row[hireDate] ?: LocalDate.now(),
        salary = row[salary] ?: 0,
        departmentId = row[departmentId] ?: 0
    )
}