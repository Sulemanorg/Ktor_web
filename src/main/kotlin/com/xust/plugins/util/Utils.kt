package com.xust.plugins.util

import java.util.*
import kotlin.collections.HashMap

/**
 *
 *
 * @author Liang on 2022/1/17
 */

fun getProperties(fileName: String): Map<String, String> {
    val resourceBundle = ResourceBundle.getBundle(fileName)
    val keys = resourceBundle.keys
    val res = HashMap<String, String>()
    while (keys.hasMoreElements()) {
        val key = keys.nextElement().toString()
        val value = resourceBundle.getString(key)
        res[key] = value
    }
    return res
}
