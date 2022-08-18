package com.github.openexw.convto.data

enum class ColumnFieldType {
    // 整数类型
    TINYINT, SMALLINT, MEDIUMINT, INT, BIGINT,

    // 浮点数类型
    FLOAT, DOUBLE, DECIMAL,

    // 字符串
    CHAR, VARCHAR, TEXT, MEDIUMTEXT, LONGTEXT,

    // 时间类型
    TIME, DATE, DATETIME, TIMESTAMP,

    // 其他类型
    ENUM, SET, BLOB
}

