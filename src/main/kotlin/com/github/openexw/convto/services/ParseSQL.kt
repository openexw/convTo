package com.github.openexw.convto.services

import cn.hutool.core.util.StrUtil
import com.github.openexw.convto.data.*
import com.intellij.ui.layout.panel
import net.sf.jsqlparser.parser.CCJSqlParserUtil
import net.sf.jsqlparser.statement.create.table.ColumnDefinition
import net.sf.jsqlparser.statement.create.table.CreateTable

class ParseSQL(val sql: String) {

    fun xx() {
        val panel = panel {
            noteRow("Proto Fields From SQL")
//            right
        }
        //    println("Hello World!")
        // 类型列表
        val typeList = hashMapOf(
            ColumnFieldType.TINYINT to GoType.INT8,
            ColumnFieldType.SMALLINT to GoType.INT16,
            ColumnFieldType.MEDIUMINT to GoType.INT32,
            ColumnFieldType.INT to GoType.INT64,
            ColumnFieldType.BIGINT to GoType.INT64,
            ColumnFieldType.FLOAT to GoType.FLOAT32,
            ColumnFieldType.DOUBLE to GoType.FLOAT64,
            ColumnFieldType.DECIMAL to GoType.FLOAT64,
            ColumnFieldType.CHAR to GoType.STRING,
            ColumnFieldType.VARCHAR to GoType.STRING,
            ColumnFieldType.TEXT to GoType.STRING,
            ColumnFieldType.MEDIUMTEXT to GoType.STRING,
            ColumnFieldType.LONGTEXT to GoType.STRING,
            ColumnFieldType.TIME to GoType.TIME_TIME,
            ColumnFieldType.DATE to GoType.TIME_TIME,
            ColumnFieldType.DATETIME to GoType.TIME_TIME,
            ColumnFieldType.TIMESTAMP to GoType.INT64,
            ColumnFieldType.ENUM to GoType.STRING,
            ColumnFieldType.SET to GoType.STRING,
            ColumnFieldType.BLOB to GoType.STRING
        )
//        val sql = """
//        CREATE TABLE if not exists `user_success_story_sleeps`
//(
//    `id`               bigint        NOT NULL AUTO_INCREMENT comment '主键id',
//    `encrypt_code`     varchar(191)  NOT NULL                default '' comment '用户故事 code',
//    `code`             varchar(191)  not null                DEFAULT '' comment '睡眠 code',
//    `content`          varchar(1000) not null                default '' comment '文字描述',
//    `data`             text comment '睡眠记录数据',
//    `pointer_table`    varchar(191)  not null                DEFAULT '' comment '下一个节点类型',
//    `pointer_table_id` varchar(191)  not null                DEFAULT '' comment '下一个节点的 code',
//    `created_at`       datetime                              DEFAULT NULL comment '创建时间',
//    `updated_at`       datetime                              DEFAULT NULL comment '更新时间',
//    PRIMARY KEY (`id`),
//    UNIQUE KEY `uk_encrypt_code_code` (`encrypt_code`, `code`)
//    ) ENGINE = InnoDB
//    CHARACTER SET = utf8mb4
//    COLLATE = utf8mb4_general_ci COMMENT '睡眠记录';
//    """.trimIndent()

        val table: CreateTable = CCJSqlParserUtil.parse(sql) as CreateTable

        var opsSize = table.tableOptionsStrings.size - 1
        var tableComment = ""
        while (opsSize >= 0) {
            if (table.tableOptionsStrings[opsSize].lowercase() == "comment") {
                tableComment = getComment(table.tableOptionsStrings[opsSize + 1])
            }
            opsSize--
        }
        val realTableName = getName(table.table.name)

        val tableColumns = ArrayList<GoField>()
        for (item in table.columnDefinitions) {
            val colName = getName(item.columnName)
            var typ: GoType = GoType.STRING

            val colType = ColumnFieldType.valueOf(item.colDataType.dataType.toString())

            if (typeList.containsKey(colType)) {
                typ = typeList.getValue(colType)
            }

            val tags = addTag(item.columnName.replace("`", ""))
            val col = GoField(colName, typ, getComment(item), tags)
            tableColumns.add(col)
        }
        val structStr = GoStruct(realTableName, tableComment, tableColumns).toString()
        println(structStr)
    }

    fun getComment(item: ColumnDefinition): String {
        var size = item.columnSpecs.size - 1
        var comment = ""
        while (size >= 0) {
            if (item.columnSpecs[size].lowercase() == "comment") {
                comment = getComment(item.columnSpecs[size + 1])
            }
            size--
        }
        return comment
    }

    fun getName(str: String): String {
        // 如果是指定的字段则返回大写字母
        if (specialIdentifier(str)) {
            return str.uppercase()
        }
        return StrUtil.upperFirst(StrUtil.toCamelCase(str.replace("`", "")))
    }

    fun getComment(str: String): String {
        return str.replace("'", "").replace("\"", "")
    }

    fun addTag(field: String): String {
        val tagList = mapOf(1 to "json", 2 to "gorm", 6 to "form")
//    val tagList = mapOf(1 to "json", 2 to "gorm", 3 to "mapstructure", 4 to "xorm", 5 to "validate", 6 to "form")
        var str = "`"
        for (tag in tagList) {
            str += tag.value + ":\"" + field + "\" "
        }
        str = StrUtil.trim(str) + "`"
        return str
    }

//fun
// TableName 表名称
//func (*SystemUser) TableName() string {
//    return "system_user"
//}

    // 特殊标识
    fun specialIdentifier(col: String): Boolean {
        val list = arrayOf("id", "url")
        return list.contains(col)
    }
}