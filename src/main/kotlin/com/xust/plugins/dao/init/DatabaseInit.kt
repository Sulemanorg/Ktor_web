package com.xust.plugins.dao.init

import com.alibaba.druid.pool.DruidDataSource
import com.xust.plugins.util.getProperties
import org.ktorm.database.Database

/**
 * datasource configuration
 *
 * @author Liang on 2022/1/17
 */

internal val databaseProperties = getProperties("database")
internal val URL = databaseProperties["url"]
internal val USERNAME = databaseProperties["username"]
internal val PASSWORD = databaseProperties["password"]
internal val DRIVER = databaseProperties["driver"]

internal val DATABASE = Database.connect(DruidDataSource().apply {
    this.url = URL
    this.username = USERNAME
    this.password = PASSWORD
    this.driver = Class.forName(DRIVER).getDeclaredConstructor().newInstance() as java.sql.Driver
})