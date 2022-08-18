package com.github.openexw.convto.services

import cn.hutool.core.util.StrUtil
import com.intellij.ui.layout.panel
import net.sf.jsqlparser.parser.CCJSqlParserUtil
import net.sf.jsqlparser.statement.create.table.CreateTable

class SQLToStruct {

    fun xx() {
        val panel = panel {
            noteRow("Proto Fields From SQL")
//            right
        }
        //    println("Hello World!")
        // 类型列表
        val typeList = hashMapOf(
            "tinyint" to "int8",
            "smallint" to "int16",
            "mediumint" to "int32",
            "int" to "int64",
            "bigint" to "int64",
            "float" to "float32",
            "double" to "float64",
            "decimal" to "float64",
            "char" to "string",
            "varchar" to "string",
            "text" to "string",
            "mediumtext" to "string",
            "longtext" to "string",
            "time" to "time.Time",
            "date" to "time.Time",
            "datetime" to "time.Time",
            "timestamp" to "int64",
            "enum" to "string",
            "set" to "string",
            "blob" to "string"
        )
        val sql = """
        CREATE TABLE if not exists `user_success_story_sleeps`
(
    `id`               bigint        NOT NULL AUTO_INCREMENT comment '主键id',
    `encrypt_code`     varchar(191)  NOT NULL                default '' comment '用户故事 code',
    `code`             varchar(191)  not null                DEFAULT '' comment '睡眠 code',
    `content`          varchar(1000) not null                default '' comment '文字描述',
    `data`             text comment '睡眠记录数据',
    `pointer_table`    varchar(191)  not null                DEFAULT '' comment '下一个节点类型',
    `pointer_table_id` varchar(191)  not null                DEFAULT '' comment '下一个节点的 code',
    `created_at`       datetime                              DEFAULT NULL comment '创建时间',
    `updated_at`       datetime                              DEFAULT NULL comment '更新时间',
    PRIMARY KEY (`id`),
    UNIQUE KEY `uk_encrypt_code_code` (`encrypt_code`, `code`)
    ) ENGINE = InnoDB
    CHARACTER SET = utf8mb4
    COLLATE = utf8mb4_general_ci COMMENT '睡眠记录';
    """.trimIndent()
        // Try adding program arguments via Run/Debug configuration.
        // Learn more about running applications: https://www.jetbrains.com/help/idea/running-applications.html.
//        println("Program arguments: ${args.joinToString()}")


        val table: CreateTable = CCJSqlParserUtil.parse(sql) as CreateTable

        var opsSize = table.tableOptionsStrings.size - 1
        var tableComment = ""
        while (opsSize >= 0) {
            if (table.tableOptionsStrings[opsSize].lowercase() == "comment") {
                tableComment = getComment(table.tableOptionsStrings[opsSize + 1])
            }
            opsSize--
        }
        var realTableName = getName(table.table.name)
        var str = ""
        if (tableComment.length > 0) {
            str += "// $realTableName $tableComment\n"
        }

        str += "type $realTableName struct { \n"
        for (item in table.columnDefinitions) {
            var size = item.columnSpecs.size - 1
            var comment = ""
            var typ = ""
            while (size >= 0) {
                if (item.columnSpecs[size].lowercase() == "comment") {
                    comment = getComment(item.columnSpecs[size + 1])
                }
                size--
            }

            val colType = item.colDataType.dataType.toString()
            if (typeList.containsKey(colType)) {
                typ = typeList.getValue(colType)
//            println(item.columnName + " " + typ + " // " + comment)
            }
            val colName = getName(item.columnName)
            val tags = addTag(item.columnName.replace("`", ""))
            str += "\t $colName $typ $tags"
            if (comment.isNotEmpty()) {
                str += "// $comment"
            }
            str += "\n"
        }
        str += "}"

        println(str)
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