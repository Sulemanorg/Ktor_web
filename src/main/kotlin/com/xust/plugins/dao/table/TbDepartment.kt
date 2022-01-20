package com.xust.plugins.dao.table

import com.xust.plugins.entity.Department
import org.ktorm.dsl.QueryRowSet
import org.ktorm.schema.*

/**
 *
 *
 * @author Liang on 2022/1/16
 */

object TbDepartment : BaseTable<Department>("t_department") {

    val id = int("id").primaryKey()
    val name = varchar("name")
    val location = varchar("location")

    override fun doCreateEntity(row: QueryRowSet, withReferences: Boolean) = Department(
        id = row[id] ?: 0,
        name = row[name].orEmpty(),
        location = row[location].orEmpty()
    )
}

