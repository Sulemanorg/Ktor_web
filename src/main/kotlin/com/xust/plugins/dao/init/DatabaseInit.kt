package com.xust.plugins.dao.init

import com.alibaba.druid.pool.DruidDataSource
import com.xust.plugins.util.getProperties
import org.ktorm.database.Database

/**
 * datasource configuration
 *
 * @author Liang on 2022/1/17
 */

private val databaseProperties = getProperties("database")
private val URL = databaseProperties["url"]
private val USERNAME = databaseProperties["username"]
private val PASSWORD = databaseProperties["password"]
private val DRIVER = databaseProperties["driver"]

internal val DATABASE = Database.connect(DruidDataSource().apply {
    this.url = URL
    this.username = USERNAME
    this.password = PASSWORD
    this.driver = Class.forName(DRIVER).getDeclaredConstructor().newInstance() as java.sql.Driver
})